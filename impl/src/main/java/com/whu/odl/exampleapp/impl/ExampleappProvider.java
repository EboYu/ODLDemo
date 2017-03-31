/*
 * Copyright © 2017 Copyright (c) 2017 Yinbo and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.whu.odl.exampleapp.impl;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.*;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.controller.sal.common.util.Rpcs;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.AnimalService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.MakeCatInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.MakeChickenInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.MakeDogInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.CatService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.MewoInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.MewoInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.ChickenService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.GegeInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.GegeInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.BarkInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.BarkInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.DogService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev170323.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.rev170323.ExampleappService;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.rev170323.ServiceAccountInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.rev170323.ServiceAccountOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.rev170323.ServiceAccountOutputBuilder;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

public class ExampleappProvider implements ExampleappService {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleappProvider.class);
    private RpcRegistration<ExampleappService> rpcService;
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private CatService catService;
    private DogService dogService;
    private ChickenService chickenService;
    private AnimalService animalService;
    private final DataBroker dataBroker;
    private final RpcProviderRegistry rpcRegistry;

    public ExampleappProvider(DataBroker dataBroker, RpcProviderRegistry rpcRegistry, CatService catProvider, DogService dogProvider, ChickenService chickenProvider, AnimalService animalService){
        this.catService = catProvider;
        this.dogService = dogProvider;
        this.chickenService = chickenProvider;
        this.dataBroker = dataBroker;
        this.rpcRegistry = rpcRegistry;
        this.animalService = animalService;
    }

    public void close() throws Exception {
        LOG.info("ExampleProvider Closed");
    }

    @Override
    public Future<RpcResult<ServiceAccountOutput>> serviceAccount(ServiceAccountInput input) {

        /*MewoInputBuilder mewoInputBuilder = new MewoInputBuilder();
        mewoInputBuilder.setNum((long)3);
        mewoInputBuilder.setTraceID("1");
        ListenableFuture<RpcResult<Void>> makeCatFuture = makeMewo(mewoInputBuilder.build());
        BarkInputBuilder barkInputBuilder = new BarkInputBuilder();
        barkInputBuilder.setNum((long)3);
        barkInputBuilder.setTraceID("2");
        ListenableFuture<RpcResult<Void>> makeDogFuture = makeBark(barkInputBuilder.build());
        GegeInputBuilder gegeInputBuilder = new GegeInputBuilder();
        gegeInputBuilder.setNum((long)3);
        gegeInputBuilder.setTraceID("3");
        ListenableFuture<RpcResult<Void>> makeChickenFuture = makeGege(gegeInputBuilder.build());

        ListenableFuture<List<RpcResult<Void>>> combinedFutures = Futures.allAsList(ImmutableList.of(makeCatFuture, makeDogFuture,makeChickenFuture));

        Futures.transform(combinedFutures, new AsyncFunction<List<RpcResult<Void>>, RpcResult<Void>>() {
            @Override
            public ListenableFuture<RpcResult<Void>> apply(List<RpcResult<Void>> input) throws Exception {
                boolean atLeastOneSucceed = false;
                ImmutableList.Builder<RpcError> errorList = ImmutableList.builder();
                for (RpcResult<Void> result : input) {
                    if (result.isSuccessful()) {
                        atLeastOneSucceed = true;
                    }
                    if (result.getErrors() != null) {
                        errorList.addAll(result.getErrors());
                    }
                }
                return Futures.immediateFuture(Rpcs.<Void>getRpcResult(atLeastOneSucceed, errorList.build()));
            }
        });*/
        for(long i=0;i<input.getNum();i++){
            if(input.getName().equals("execution"))
            {
                executeMakeCat(input.getTraceID(),1);
                executeMakeDog(input.getTraceID(),1);
                executeMakeChicken(input.getTraceID(),1);
            }
        }

        ServiceAccountOutputBuilder serviceAccBuilder = new ServiceAccountOutputBuilder();
        serviceAccBuilder.setServiceName("RPC request successful for Service: " + input.getName());
        return RpcResultBuilder.success(serviceAccBuilder.build()).buildFuture();
    }



    private ListenableFuture<RpcResult<Void>> makeMewo(final MewoInput input) {
         MewoInput mewoInput = new MewoInputBuilder()
                .setNum(input.getNum())
                .setTraceID(input.getTraceID()).build();
        return JdkFutureAdapters.listenInPoolThread(catService.mewo(mewoInput));
    }

    private ListenableFuture<RpcResult<Void>> makeBark(final BarkInput input) {
        BarkInput mewoInput = new BarkInputBuilder()
                .setNum(input.getNum())
                .setTraceID(input.getTraceID()).build();
        return JdkFutureAdapters.listenInPoolThread(dogService.bark(mewoInput));
    }

    private ListenableFuture<RpcResult<Void>> makeGege(final GegeInput input) {
        GegeInput mewoInput = new GegeInputBuilder()
                .setNum(input.getNum())
                .setTraceID(input.getTraceID()).build();
        return JdkFutureAdapters.listenInPoolThread(chickenService.gege(mewoInput));
    }

    private void executeMakeCat(String traceid,long num) {
        final MakeCatInputBuilder builder = new MakeCatInputBuilder();
        builder.setTraceID(traceid).setNum(num);
        final Future<RpcResult<Void>> rpcResultFuture = animalService.makeCat(builder.build());
        try {
            if (!rpcResultFuture.get().isSuccessful()) {
            }

        } catch (InterruptedException | ExecutionException e) {
        }
    }

    private void executeMakeDog(String traceid,long num) {
        final MakeDogInputBuilder builder = new MakeDogInputBuilder();
        builder.setTraceID(traceid).setNum(num);
        final Future<RpcResult<Void>> rpcResultFuture = animalService.makeDog(builder.build());
        try {
            if (!rpcResultFuture.get().isSuccessful()) {
            }

        } catch (InterruptedException | ExecutionException e) {
        }
    }

    private void executeMakeChicken(String traceid,long num) {
        final MakeChickenInputBuilder builder = new MakeChickenInputBuilder();
        builder.setTraceID(traceid).setNum(num);
        final Future<RpcResult<Void>> rpcResultFuture = animalService.makeChicken(builder.build());
        try {
            if (!rpcResultFuture.get().isSuccessful()) {
            }

        } catch (InterruptedException | ExecutionException e) {
        }
    }

    /*private ListenableFuture<RpcResult<Void>> makeCat(final ServiceAccountInput input) {
        final SettableFuture<RpcResult<Void>> futureResult = SettableFuture.create();
        executor.submit(new MakeCatTask(input, futureResult));
        return futureResult;
    }

    private  class MakeCatTask implements Callable<Void> {

        final ServiceAccountInput makeRequest;
        final SettableFuture<RpcResult<Void>> futureResult;

        public MakeCatTask(ServiceAccountInput makeRequest, SettableFuture<RpcResult<Void>> futureResult) {
            this.makeRequest = makeRequest;
            this.futureResult = futureResult;
        }

        @Override
        public Void call() throws Exception {
            try {
                LOG.info("Making Cat, Sleep for 1 Seconds");
                Thread.sleep(1000);
                futureResult.set(RpcResultBuilder.<Void>success().build());
            } catch (InterruptedException e) {
                LOG.info("Interrupted when making the Cat: {}", e);
                futureResult.set(RpcResultBuilder.<Void>failed().withError(RpcError.ErrorType.APPLICATION,
                        e.getMessage()).build());
            }
            return null;
        }
    }

    private ListenableFuture<RpcResult<Void>> makeDog(final ServiceAccountInput input) {
        final SettableFuture<RpcResult<Void>> futureResult = SettableFuture.create();
        executor.submit(new MakeDogTask(input, futureResult));
        return futureResult;
    }

    private  class MakeDogTask implements Callable<Void> {

        final ServiceAccountInput makeRequest;
        final SettableFuture<RpcResult<Void>> futureResult;

        public MakeDogTask(ServiceAccountInput makeRequest, SettableFuture<RpcResult<Void>> futureResult) {
            this.makeRequest = makeRequest;
            this.futureResult = futureResult;
        }

        @Override
        public Void call() throws Exception {
            try {
                LOG.info("Making Dog, Sleep for 0.8 Seconds");
                Thread.sleep(800);
                futureResult.set(RpcResultBuilder.<Void>success().build());
            } catch (InterruptedException e) {
                LOG.info("Interrupted when making the Dog: {}", e);
                futureResult.set(RpcResultBuilder.<Void>failed().withError(RpcError.ErrorType.APPLICATION,
                        e.getMessage()).build());
            }
            return null;
        }
    }

    private ListenableFuture<RpcResult<Void>> makeChicken(final ServiceAccountInput input) {
        final SettableFuture<RpcResult<Void>> futureResult = SettableFuture.create();
        executor.submit(new MakeChickenTask(input, futureResult));
        return futureResult;
    }

    private  class MakeChickenTask implements Callable<Void> {

        final ServiceAccountInput makeRequest;
        final SettableFuture<RpcResult<Void>> futureResult;

        public MakeChickenTask(ServiceAccountInput makeRequest, SettableFuture<RpcResult<Void>> futureResult) {
            this.makeRequest = makeRequest;
            this.futureResult = futureResult;
        }

        @Override
        public Void call() throws Exception {
            try {
                LOG.info("Making Chicken, Sleep for 1.2 Seconds");
                Thread.sleep(1200);
                futureResult.set(RpcResultBuilder.<Void>success().build());
            } catch (InterruptedException e) {
                LOG.info("Interrupted when making the Chicken: {}", e);
                futureResult.set(RpcResultBuilder.<Void>failed().withError(RpcError.ErrorType.APPLICATION,
                        e.getMessage()).build());
            }
            return null;
        }
    }*/
}
