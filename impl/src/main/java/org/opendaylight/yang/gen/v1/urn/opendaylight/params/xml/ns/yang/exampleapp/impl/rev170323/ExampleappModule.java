package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev170323;

import com.whu.odl.exampleapp.impl.CatProvider;
import com.whu.odl.exampleapp.impl.ChickenProvider;
import com.whu.odl.exampleapp.impl.DogProvider;
import com.whu.odl.exampleapp.impl.ExampleappProvider;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.animal.rev170323.AnimalService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cat.rev170323.CatService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.chicken.rev170323.ChickenService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.dog.rev170323.DogService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.rev170323.ExampleappService;

public class ExampleappModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev170323.AbstractExampleappModule {
    public ExampleappModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public ExampleappModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev170323.ExampleappModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        // TODO:implement
        final CatService catService = getRpcRegistryDependency().getRpcService(CatService.class);
        final DogService dogService = getRpcRegistryDependency().getRpcService(DogService.class);
        final ChickenService chickenService = getRpcRegistryDependency().getRpcService(ChickenService.class);
        final AnimalService animalService = getRpcRegistryDependency().getRpcService(AnimalService.class);
        final ExampleappProvider exampleappProvider = new ExampleappProvider(getDataBrokerDependency(),getRpcRegistryDependency(),catService,dogService,chickenService, animalService);
        final BindingAwareBroker.RpcRegistration<ExampleappService> exampleappServiceRpcRegistration = getRpcRegistryDependency().addRpcImplementation(ExampleappService.class,exampleappProvider);
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                exampleappProvider.close();
                exampleappServiceRpcRegistration.close();
            }
        };
    }

}
