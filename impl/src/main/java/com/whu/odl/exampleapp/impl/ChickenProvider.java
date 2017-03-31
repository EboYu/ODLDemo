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
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.ChickenService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.GegeInput;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class ChickenProvider implements ChickenService{
    private static final Logger LOG = LoggerFactory.getLogger(ExampleappProvider.class);

    private final DataBroker dataBroker;
    private final RpcProviderRegistry rpcRegistry;


    public ChickenProvider(DataBroker dataBroker, RpcProviderRegistry rpcRegistry){
        this.dataBroker =dataBroker;
        this.rpcRegistry = rpcRegistry;
    }

    public void close() throws Exception {
        LOG.info("ChickenProvider Closed");
    }

    @Override
    public Future<RpcResult<Void>> gege(GegeInput input) {
        makeChicken(input.getNum());
        return RpcResultBuilder.<Void>success().buildFuture();
        //return Futures.immediateFuture(RpcResultBuilder.<Void>success().build());
    }

    private void makeChicken(long num){
        for(long i=0; i<num;i++){
            try {
                LOG.info("Making Chicken, Sleep for 1.2 Seconds");
                Thread.sleep(1200);

            } catch (InterruptedException e) {
                LOG.info("Interrupted when making the Chicken: {}", e);
            }
        }
    }
}
