package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.impl.rev170323;

import com.whu.odl.exampleapp.impl.ChickenProvider;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.ChickenService;

public class ChickenModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.impl.rev170323.AbstractChickenModule {
    public ChickenModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public ChickenModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.impl.rev170323.ChickenModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        final ChickenProvider chickenProvider = new ChickenProvider(getDataBrokerDependency(),getRpcRegistryDependency());
        final BindingAwareBroker.RpcRegistration<ChickenService> chickenServiceRpcRegistration = getRpcRegistryDependency().addRpcImplementation(ChickenService.class,chickenProvider);
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                chickenProvider.close();
                chickenServiceRpcRegistration.close();
            }
        };
    }

}
