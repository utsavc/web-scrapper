package com.buildtmwlearning.webscrapper.service;

import com.buildtmwlearning.webscrapper.entities.Scrapper;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvUtils {

    public boolean createCsv(List<Scrapper> scraps) throws Exception{

        Writer writer = Files.newBufferedWriter(Paths.get("scraps.csv"));

        CustomMappingStrategy columnStrategy = new CustomMappingStrategy();
        columnStrategy.setType(Scrapper.class);

        StatefulBeanToCsv csvBuilder =new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(columnStrategy)
                .build();

        csvBuilder.write(scraps);
        System.out.println(scraps);
        writer.close();
        return true;
    }



    private static class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
        @Override
        public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
            final int numColumns = getFieldMap().values().size();
            super.generateHeader(bean);

            String[] customHeader = new String[numColumns];

            BeanField<T, Integer> beanField;
            for(int i=0;i<numColumns;i++){
                beanField = findField(i);
                String columnHeaderName = extractHeaderName(beanField);
                customHeader[i]=columnHeaderName;
            }
            return customHeader;
        }


        private String extractHeaderName(BeanField<T, Integer> beanField){
            if(beanField ==null || beanField.getField() ==null
                    || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length==0){
                return StringUtils.EMPTY;
            }
            final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];
            return bindByNameAnnotation.column();
        }
    }
}