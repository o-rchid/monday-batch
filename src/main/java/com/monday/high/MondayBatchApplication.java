package com.monday.high;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MondayBatchApplication {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	public MondayBatchApplication(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Step passStep(){
		return this.stepBuilderFactory.get("passStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Execute PassStep");
					return RepeatStatus.FINISHED;
				}).build();
	}

	@Bean
	public Job passJob(){
		return this.jobBuilderFactory.get("passJob")
				.start(passStep())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MondayBatchApplication.class, args);
	}

}
