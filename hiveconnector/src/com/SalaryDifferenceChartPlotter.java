package com;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.util.ShapeUtilities;

public class SalaryDifferenceChartPlotter {
	
	private static String outputFilePath = "/Users/Basak/Desktop/salary_difference_chart.jpg";
	
	public void plotChart() throws SQLException, IOException {
		 JFreeChart chart = ChartFactory.createBarChart("Dashboard",
                 null, "Test Started (per Hour)", new HiveDataFetcher().getDataFromHive(), PlotOrientation.VERTICAL,
                 true, true, false);
		 
		 chart.setBackgroundPaint(Color.white);
         chart.setBorderPaint(Color.WHITE);

         chart.getLegend().setBackgroundPaint(new Color(233,236,244));


         final CategoryPlot plot = chart.getCategoryPlot();

         plot.setBackgroundPaint(Color.white);
         plot.getRangeAxis().setLabelFont(new Font("Arial", Font.BOLD, 15));



         plot.getDomainAxis().setTickLabelPaint(Color.DARK_GRAY);
         plot.getDomainAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 10));

         plot.getRangeAxis().setTickLabelPaint(Color.DARK_GRAY);
         plot.getRangeAxis().setTickLabelFont(new Font("Arial", Font.BOLD, 15));


         CategoryItemRenderer renderer = plot.getRenderer();


         renderer.setSeriesStroke(0,new BasicStroke(2f));


         renderer.setSeriesShape(0, ShapeUtilities.createDiamond(5) );

         renderer.setSeriesPaint(0,new Color(90,155,59));

         plot.setRenderer(renderer);
         System.out.println("Saving chart as JPEG...");
         ChartUtilities.saveChartAsJPEG(new File(outputFilePath), chart, 800, 400);
         System.out.println("Chart saved at "+ outputFilePath);
	}
	
	public static void main (String args[]) throws SQLException, IOException{
        new SalaryDifferenceChartPlotter().plotChart();
    }

}
