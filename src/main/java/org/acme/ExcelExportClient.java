package main.java.org.acme;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.quarkus.hello.ExcelExportRequest;
import io.quarkus.hello.ExcelExportResponce;
import io.quarkus.hello.ExcelExportServicegRPCGrpc;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExcelExportClient {
    
    public byte[] ExcelExport()
    {
        ManagedChannel channel = ManagedChannelBuilder
                                 .forAddress("localhost" , 8081 )
                                 .usePlaintext()
                                 .build();

        ExcelExportServicegRPCGrpc.ExcelExportServicegRPCBlockingStub BlockingStub = ExcelExportServicegRPCGrpc
                                                                                     .newBlockingStub(channel);

        ExcelExportRequest requestExcel = ExcelExportRequest
                                          .newBuilder()
                                          .setObjectName("user").build();

        ExcelExportResponce responceExcel  = BlockingStub.excelExportgRPC(requestExcel);
        byte [] excelData = responceExcel.getExcelFile().toByteArray();

        return excelData;
    }
}
