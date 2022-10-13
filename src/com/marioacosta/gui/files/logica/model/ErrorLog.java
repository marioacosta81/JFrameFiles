/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marioacosta.gui.files.logica.model;

/**
 *
 * @author MAAACOST
 */
public class ErrorLog implements  Comparable<ErrorLog>  {
	
	private String traza;
	private String trazaId;
	private String servicio; 
	private String tipoLog;
	private String archivo;
	private int ocurrencias;
	
	
	public String getTraza() {
		return traza;
	}
	public void setTraza(String traza) {
		this.traza = traza;
	}
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public int getOcurrencias() {
		return ocurrencias;
	}
	public void setOcurrencias(int ocurrencias) {
		this.ocurrencias = ocurrencias;
	}
	
	public String getTrazaId() {
		return trazaId;
	}
	public void setTrazaId(String trazaId) {
		this.trazaId = trazaId;
	}
	
	
	
	
	
	public String getTipoLog() {
		return tipoLog;
	}
	public void setTipoLog(String tipoLog) {
		this.tipoLog = tipoLog;
	}
	@Override
	public int compareTo(ErrorLog o) {
		
		if(null == o) {
			return -1;
		}
		return this.trazaId.compareTo(o.getTrazaId());
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.trazaId.hashCode()+this.ocurrencias;
	}
	@Override
	public boolean equals(Object obj) {

		if(null == obj || !(obj instanceof ErrorLog)) {
			return false;
		}
		
		return this.getTrazaId().equals(   ((ErrorLog)obj).getTrazaId()    );
	}
	@Override
	public String toString() {
		return this.trazaId +"\n" +this.traza +"\n" +this.archivo +"\n" +this.servicio +"\n" +this.ocurrencias +"\n";
	}
	
	
	
		
	
	
	

}
