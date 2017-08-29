package com.talentsearch.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.talentsearch.db.util.SQLQuery;

public class StartService {

	public static void setSchema() throws IOException{
		if(SQLQuery.dbschema == ""){
			Properties props = System.getProperties();
			 FileInputStream fs = new FileInputStream("dbdetails.properties");
			 props.load(fs);
			 fs.close();
			 SQLQuery.dbschema = props.getProperty("mysql.schema");
		}
	}
}
