package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.impl.rev170323;

import com.whu.odl.exampleapp.impl.DogProvider;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.DogService;

public class DogModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.impl.rev170323.AbstractDogModule {
    public DogModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public DogModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.impl.rev170323.DogModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        final DogProvider dogProvider = new DogProvider(getDataBrokerDependency(),getRpcRegistryDependency());
        final BindingAwareBroker.RpcRegistration<DogService> dogServiceRpcRegistration = getRpcRegistryDependency().addRpcImplementation(DogService.class,dogProvider);
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                dogProvider.close();
                dogServiceRpcRegistration.close();
            }
        };
    }

}
