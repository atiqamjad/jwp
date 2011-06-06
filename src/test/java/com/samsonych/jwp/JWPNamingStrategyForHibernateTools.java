package com.samsonych.jwp;

import com.samsonych.jwp.service.dba.JWPNamingStrategy;

public class JWPNamingStrategyForHibernateTools extends JWPNamingStrategy {

    private static final long serialVersionUID = -3343227712705842689L;

    public JWPNamingStrategyForHibernateTools() {
        super();
        this.setTablePrefix("wp_");
    }

}