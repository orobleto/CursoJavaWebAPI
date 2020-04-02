
package com.curso.java.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para convertirCadaPalabraCadenaMinuscula complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="convertirCadaPalabraCadenaMinuscula">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cadena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "convertirCadaPalabraCadenaMinuscula", namespace = "convertirCadaPalabraCadenaMinuscula", propOrder = {
		"cadena" })
public class ConvertirCadaPalabraCadenaMinuscula {

	protected String cadena;

	/**
	 * Obtiene el valor de la propiedad cadena.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCadena() {
		return cadena;
	}

	/**
	 * Define el valor de la propiedad cadena.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setCadena(String value) {
		this.cadena = value;
	}

}
