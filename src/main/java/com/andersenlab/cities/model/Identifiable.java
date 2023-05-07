package com.andersenlab.cities.model;

import java.io.Serializable;

/**
 * @author Aliaksei Tumilovich
 */
public interface Identifiable<T extends Serializable> extends Serializable {
	T getById();
}
