package com.innovativeintelli.configurationmgmt.repository;

import java.io.InputStream;

public interface ConfigurationRepository {

	String readLibraryExcel(InputStream is, String originalFilename);

}
