package io.bootify.davidtinaut_spring_batch_banco.config;


import io.bootify.davidtinaut_spring_batch_banco.model.TransaccionesDTO;
import io.bootify.davidtinaut_spring_batch_banco.controller.FileDeletingTasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Value("file:src/main/resources/*.csv")
    private Resource[] inputResources;

    @Bean
    public Job job( JobBuilderFactory jobBuilderFactory,
                    StepBuilderFactory stepBuilderFactory,
                    ItemReader<TransaccionesDTO> itemReader,
                    ItemProcessor<TransaccionesDTO, TransaccionesDTO> itemProcessor,
                    ItemWriter<TransaccionesDTO> itemWriter )
    {

        Step step = stepBuilderFactory.get("readCsvFileStep")
                .<TransaccionesDTO, TransaccionesDTO>chunk(200)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .faultTolerant()
                .skipLimit(5000)
                .skip(Exception.class)
                .build();
        System.out.println("Employee reading step başarılı bir şekilde tamamlandı.");

        FileDeletingTasklet task = new FileDeletingTasklet();
        task.setResources(inputResources);
        Step step2 = stepBuilderFactory.get("deleteCsvFileStep")
                .tasklet(task)
                .build();

        System.out.println("After delete file");

        Job job = jobBuilderFactory.get("readCsvFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .next(step2)
                .build();
        System.out.println("Job başarılı bir şekilde stepleri tamamladı.");
        return job;
    }


    @Bean
    public FlatFileItemReader<TransaccionesDTO> itemReader() {

        FlatFileItemReader<TransaccionesDTO> flatFileItemReader = new FlatFileItemReader<>();
        //flatFileItemReader.setResource(new FileSystemResource("src/main/resources/transacciones.csv"));
        String inputResource = "src/main/resources/transacciones.csv";
        //flatFileItemReader.setEncoding("UTF-8");
        flatFileItemReader.setResource(new FileSystemResource(inputResource));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        //flatFileItemReader.afterPropertiesSet();
        //flatFileItemReader.open(new ExecutionContext());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<TransaccionesDTO> lineMapper() {
        DefaultLineMapper<TransaccionesDTO> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"fecha", "importe","tipotrans", "cuentaorigen", "cuentadestino"});

        BeanWrapperFieldSetMapper<TransaccionesDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(TransaccionesDTO.class);


        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}