package otus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otus.form.Average;
import otus.form.InputAssetForm;
import otus.form.InputShareForm;
import otus.form.InputStockForm;
import otus.model.Application;
import otus.model.assets.Asset;
import otus.model.assets.InputAsset;
import otus.model.assets.InputShare;
import otus.model.assets.InputStock;
import otus.model.reports.CustomReport;
import otus.model.reports.CustomReportFactory;
import otus.model.reports.GeneralReportFactory;
import otus.model.reports.SimpleReportFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Value("${error.message}")
    private String errorMessage;

    private List<Asset> assets;
    private List<Asset> shares;
    private List<Asset> stocks;
    private List<Asset> inputAssets;
    private List<Asset> inputShares;
    private List<Asset> inputStocks;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        assets = new ArrayList<>();
        shares = new ArrayList<>();
        stocks = new ArrayList<>();
        inputAssets = new ArrayList<>();
        inputShares = new ArrayList<>();
        inputStocks = new ArrayList<>();
        return "index";
    }

    @RequestMapping(value = {"/simpleReportForm"}, method = RequestMethod.GET)
    public String showAssetPage(Model model) {

        InputAssetForm inputAssetForm = new InputAssetForm();
        model.addAttribute("inputAssetForm", inputAssetForm);

        return "simpleReportForm";
    }

    @RequestMapping(value = {"/simpleReportForm"}, method = RequestMethod.POST)
    public String saveAsset(Model model,
                            @ModelAttribute("inputAssetForm") InputAssetForm inputAssetForm) {

        String ticker = inputAssetForm.getTicker();

        if (ticker != null && ticker.length() > 0
                && inputAssets.stream().noneMatch(asset -> ticker.equals(asset.getTicker()))) {//todo ???
            InputAsset inputAsset = new InputAsset(ticker);
            inputAssets.add(inputAsset);

            return "redirect:/inputAssetList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "simpleReportForm";
    }

    @RequestMapping(value = {"/inputAssetList"}, method = RequestMethod.GET)
    public String inputAssetList(Model model) {

        model.addAttribute("inputAssets", inputAssets);

        return "inputAssetList";
    }

    @RequestMapping(value = {"/simpleReport"}, method = RequestMethod.GET)
    public String simpleReport(Model model) {
        Application app = new Application(new SimpleReportFactory());
        assets = app.report(inputAssets);
        model.addAttribute("assets", assets);
        return "simpleReport";
    }

    @RequestMapping(value = {"/generalReportForm"}, method = RequestMethod.GET)
    public String showSharePage(Model model) {

        InputShareForm inputShareForm = new InputShareForm();
        model.addAttribute("inputShareForm", inputShareForm);
        return "generalReportForm";
    }

    @RequestMapping(value = {"/generalReportForm"}, method = RequestMethod.POST)
    public String saveShare(Model model,
                            @ModelAttribute("inputShareForm") InputShareForm inputShareForm) {

        String ticker = inputShareForm.getTicker();
        BigDecimal price = inputShareForm.getPrice();
        int count = inputShareForm.getCount();

        if (ticker != null && ticker.length() > 0
                && price != null && BigDecimal.ZERO.compareTo(price) < 0
                && count > 0
                && inputShares.stream().noneMatch(share -> ticker.equals(share.getTicker()))) {//todo ???
            Asset inputShare = new InputShare(ticker, price, count);
            inputShares.add(inputShare);

            return "redirect:/inputShareList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "generalReportForm";
    }

    @RequestMapping(value = {"/inputShareList"}, method = RequestMethod.GET)
    public String inputShareList(Model model) {

        model.addAttribute("inputShares", inputShares);

        return "inputShareList";
    }

    @RequestMapping(value = {"/generalReport"}, method = RequestMethod.GET)
    public String generalReport(Model model) {
        Application app = new Application(new GeneralReportFactory());
        shares = app.report(inputShares);
        model.addAttribute("shares", shares);
        return "generalReport";
    }

    @RequestMapping(value = {"/customReportForm"}, method = RequestMethod.GET)
    public String showStockPage(Model model) {

        InputStockForm inputStockForm = new InputStockForm();
        model.addAttribute("inputStockForm", inputStockForm);

        return "customReportForm";
    }

    @RequestMapping(value = {"/customReportForm"}, method = RequestMethod.POST)
    public String saveStock(Model model,
                            @ModelAttribute("inputStockForm") InputStockForm inputStockForm) {

        String ticker = inputStockForm.getTicker();
        String sectorId = inputStockForm.getSectorId();

        if (ticker != null && ticker.length() > 0
                && sectorId != null && sectorId.length() > 0
                && inputStocks.stream().noneMatch(stock -> ticker.equals(stock.getTicker()))) {//todo ???
            Asset inputStock = new InputStock(ticker, sectorId);
            inputStocks.add(inputStock);

            return "redirect:/inputStockList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "customReportForm";
    }

    @RequestMapping(value = {"/inputStockList"}, method = RequestMethod.GET)
    public String inputStockList(Model model) {

        model.addAttribute("inputStocks", inputStocks);

        return "inputStockList";
    }

    @RequestMapping(value = {"/averageChoice"}, method = RequestMethod.GET)
    public String averageChoice(Model model) {
        model.addAttribute("averages", CustomReport.ChangeAverage.values());
        model.addAttribute("average", new Average());
        return "averageChoice";
    }

    @RequestMapping(value = {"/averageChoice"}, method = RequestMethod.POST)
    public String averageChoice(Model model, @ModelAttribute("average") Average average) {
        CustomReport.ChangeAverage changeAverage = average.getChangeAverage();
        if (changeAverage != null) {
            return "redirect:/customReport/" + changeAverage;
        }

        model.addAttribute("errorMessage", errorMessage);
        return "averageChoice";
    }

    @RequestMapping(value = {"/customReport/{average}"},method = RequestMethod.GET)
    public String customReport(Model model,
                               @PathVariable("average") CustomReport.ChangeAverage average) {
        Application app = new Application(new CustomReportFactory(average));
        stocks = app.report(inputStocks);
        model.addAttribute("stocks", stocks);
        return "customReport";
    }
}
