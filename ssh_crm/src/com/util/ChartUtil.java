package com.util;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartUtil {
	private static PieDataset getPieDataset(String[] strSource,Double[] data) {
		//创建一个饼图的数据类
		DefaultPieDataset dataset = new DefaultPieDataset();
		//向数据类添加属性
		for(int i = 0; i < strSource.length; i++) {
			dataset.setValue(strSource[i], data[i]);
		}
		return dataset;
	}
	
	//创建一个JFreeChart对象
	public static JFreeChart getJFreeChart(String[] strSource,Double[] data,String strTitle) {
		//获取数据集
		PieDataset dataset = getPieDataset(strSource,data);
		//生成JFreeChart对象
		JFreeChart chart = ChartFactory.createPieChart(strTitle ,dataset, true,true,false);
		setPiePoltFont(chart);
		setLegengTitle(chart);
		setPiePoltNum(chart);
		createPiePlot(chart);
		//关闭抗锯齿
		chart.setAntiAlias(false);
		return chart; 
	}

	private static void createPiePlot(JFreeChart chart) {
		
	}

	private static void setPiePoltNum(JFreeChart chart) {
		PiePlot piePlot = (PiePlot)chart.getPlot();
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}人，占{2}") );
		
	}

	private static void setLegengTitle(JFreeChart chart) {
		// TODO Auto-generated method stub
		
	}

	private static void setPiePoltFont(JFreeChart chart) {
		//图表
		PiePlot piePlot = (PiePlot) chart.getPlot();
		//设置图表字体
		piePlot.setLabelFont(new Font("宋体",Font.PLAIN,14));
		//标题
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体",Font.BOLD,22));
		//图例
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("宋体",Font.PLAIN,14));
	}
}
