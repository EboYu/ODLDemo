/*
 * Copyright © 2017 Copyright (c) 2017 Yinbo and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.whu.odl.exampleapp.impl;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.BarkInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.DogService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class DogProvider implements DogService {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleappProvider.class);

    private final DataBroker dataBroker;
    private final RpcProviderRegistry rpcRegistry;


    public DogProvider(DataBroker dataBroker, RpcProviderRegistry rpcRegistry){
        this.dataBroker =dataBroker;
        this.rpcRegistry = rpcRegistry;
    }

    public void close() throws Exception {
        LOG.info("DogProvider Closed");
    }

    @Override
    public Future<RpcResult<Void>> bark(BarkInput input) {
        makeDog(input.getNum());
        return Futures.immediateFuture(RpcResultBuilder.<Void>success().build());
    }

    private void makeDog(long num){
        for(long i=0; i<num;i++){
            try {
                LOG.info("Making Dog, Sleep for 0.8 Seconds");
                Thread.sleep(800);

            } catch (InterruptedException e) {
                LOG.info("Interrupted when making the Dog: {}", e);
            }
        }
    }
}
