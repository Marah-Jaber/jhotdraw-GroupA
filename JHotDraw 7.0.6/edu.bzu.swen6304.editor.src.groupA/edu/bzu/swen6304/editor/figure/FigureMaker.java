package edu.bzu.swen6304.editor.figure;

public class FigureMaker {

	private FigureFactory figureFactory;
	styleFigure drawingFigure;
	
	public FigureMaker(){
		
		figureFactory = new DefaultFigureFactory();
	}
	
	
	
	public styleFigure addStyle(FigureType figureType)
	{
		
		drawingFigure = figureFactory.getFigure(figureType);
		drawingFigure.addStyle();
		return drawingFigure;
	}
}
