package br.com.application.domain;

//@Grab(group='net.sf.jasperreports', module='jasperreports', version='6.17.0')

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.activation.DataSource;
import javax.swing.JFrame;

import org.hibernate.annotations.Immutable;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.EventQueue;
import java.awt.Dimension;


@Immutable
public class Country {
	
    String name;
    String population;
    
    public Country(String name, String population) {
		this.population = population;
		this.name = name;
	}
    
    public static List<Country> findAll() {
    	List<Country> countries = new ArrayList<>();
    	countries.add(new Country("China", "1.382.000.000"));
    	countries.add(new Country("India", "1.382.000.000"));
    	countries.add(new Country("USA", "324.666.002"));
    	countries.add(new Country("Indonesia", "260.561.008"));
    	countries.add(new Country("Brazil", "207.221.447"));
    	countries.add(new Country("Pakistan", "196.626.005"));
    	countries.add(new Country("Nigeria", "186.988.355"));
    	return countries;
    }
    
    public String getPopulation() {
		return population;
	}
    
    public void setPopulation(String population) {
		this.population = population;
	}
                           
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    

}
