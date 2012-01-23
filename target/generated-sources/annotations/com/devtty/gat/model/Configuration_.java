package com.devtty.gat.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Configuration.class)
public abstract class Configuration_ {

	public static volatile SingularAttribute<Configuration, Long> id;
	public static volatile SingularAttribute<Configuration, String> name;

}

