package com.app.dbloader.config;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.dbloader.dto.StoreOrderCSV;
import com.app.dbloader.exception.InvalidStoreDataException;
import com.app.dbloader.repository.StoreOrderRepository;


public class DBLoaderTasklet implements Tasklet {
	private static final Logger LOGGER = LoggerFactory.getLogger(DBLoaderTasklet.class);

	 @Autowired
	 private ItemStreamReader<StoreOrderCSV> itemReader;

	 @Autowired
	 private StoreOrderRepository storeOrderRepository;
	 
	
	 public DBLoaderTasklet() {

	 }

	 
	  @Override
	  @Transactional(rollbackOn= {InvalidStoreDataException.class, SQLIntegrityConstraintViolationException.class,Exception.class})
	  public RepeatStatus execute(StepContribution stepContribution,ChunkContext chunkContext) throws Exception  {
		  StoreOrderCSV item;
	      
		  try {
	            itemReader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());
	            while ((item = itemReader.read()) != null) {
	            	LOGGER.info("storing data {} ",item.toString());
	            	storeOrderRepository.save(item.toStoreOrder());
	            }
		  }catch(Exception e) {
			  LOGGER.error("error encountered : {} ",e.getMessage());
			  throw e;
		  }finally {
			  itemReader.close();
		  }
		  
	    return RepeatStatus.FINISHED;
	  }
} 

