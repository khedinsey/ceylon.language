package com.redhat.ceylon.compiler.java.runtime.metamodel;

import java.lang.annotation.Annotation;
import java.util.List;

import ceylon.language.Iterator;
import ceylon.language.Sequential;
import ceylon.language.empty_;
import ceylon.language.finished_;
import ceylon.language.model.declaration.ClassDeclaration$impl;
import ceylon.language.model.declaration.FunctionalDeclaration$impl;

import com.redhat.ceylon.compiler.java.Util;
import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.metadata.Sequenced;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.typechecker.model.Functional;
import com.redhat.ceylon.compiler.typechecker.model.Parameter;
import com.redhat.ceylon.compiler.typechecker.model.ParameterList;

@Ceylon(major = 5)
@com.redhat.ceylon.compiler.java.metadata.Class
public class FreeClass 
    extends FreeClassOrInterface
    implements ceylon.language.model.declaration.ClassDeclaration {

    @Ignore
    public static final TypeDescriptor $TypeDescriptor = TypeDescriptor.klass(FreeClass.class);
    private Sequential<? extends ceylon.language.model.declaration.FunctionOrValueDeclaration> parameters;
    
    public FreeClass(com.redhat.ceylon.compiler.typechecker.model.Class declaration) {
        super(declaration);
    }
    
    @Override
    protected void init() {
        super.init();
        // anonymous classes don't have parameter lists
        if(!declaration.isAnonymous()){
            ParameterList parameterList = ((com.redhat.ceylon.compiler.typechecker.model.Class)declaration).getParameterList();
            List<Parameter> modelParameters = parameterList.getParameters();
            ceylon.language.model.declaration.FunctionOrValueDeclaration[] parameters = new ceylon.language.model.declaration.FunctionOrValueDeclaration[modelParameters.size()];
            int i=0;
            for(Parameter modelParameter : modelParameters){
                parameters[i] = (ceylon.language.model.declaration.FunctionOrValueDeclaration) Metamodel.getOrCreateMetamodel(modelParameter.getModel());
                i++;
            }
            this.parameters = Util.sequentialInstance(ceylon.language.model.declaration.FunctionOrValueDeclaration.$TypeDescriptor, parameters);
        }else{
            this.parameters = (Sequential) empty_.$get();
        }
    }
    
    @Override
    @Ignore
    public ClassDeclaration$impl $ceylon$language$model$declaration$ClassDeclaration$impl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Ignore
    public FunctionalDeclaration$impl $ceylon$language$model$declaration$FunctionalDeclaration$impl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getAnonymous(){
        return declaration.isAnonymous();
    }
    
    @Override
    @TypeInfo("ceylon.language::Sequential<ceylon.language.model.declaration::FunctionOrValueDeclaration>")
    public Sequential<? extends ceylon.language.model.declaration.FunctionOrValueDeclaration> getParameterDeclarations(){
        checkInit();
        return parameters;
    }

    @Override
    @TypeInfo("ceylon.language.model.declaration::FunctionOrValueDeclaration|ceylon.language::Null")
    public ceylon.language.model.declaration.FunctionOrValueDeclaration getParameterDeclaration(@Name("name") String name){
        checkInit();
        Iterator<?> iterator = parameters.iterator();
        Object o;
        while((o = iterator.next()) != finished_.$get()){
            ceylon.language.model.declaration.FunctionOrValueDeclaration pd = (ceylon.language.model.declaration.FunctionOrValueDeclaration) o;
            if(pd.getName().equals(name))
                return pd;
        }
        return null;
    }

    @Ignore
    @Override
    public Sequential<? extends ceylon.language.model.Type> apply$types(){
        return (Sequential) empty_.$get();
    }

    @Ignore
    @Override
    public ceylon.language.model.Class<? extends Object, ? super Sequential<? extends Object>> apply(){
        return apply(apply$types());
    }

    @Override
    @TypeInfo("ceylon.language.model::Class<ceylon.language::Anything,ceylon.language::Nothing>")
    public ceylon.language.model.Class<? extends Object, ? super Sequential<? extends Object>> apply(
            @Name("types") @Sequenced @TypeInfo("ceylon.language::Sequential<ceylon.language.model::Type>") 
            Sequential<? extends ceylon.language.model.Type> types){
        return bindAndApply(null, types);
    }

    @Ignore
    @Override
    public Sequential<? extends ceylon.language.model.Type> bindAndApply$types(Object instance){
        return (Sequential) empty_.$get();
    }

    @Ignore
    @Override
    public ceylon.language.model.Class<? extends Object, ? super Sequential<? extends Object>> bindAndApply(Object instance){
        return bindAndApply(instance, bindAndApply$types(instance));
    }

    @Override
    @TypeInfo("ceylon.language.model::Class<ceylon.language::Anything,ceylon.language::Nothing>")
    public ceylon.language.model.Class<? extends Object, ? super Sequential<? extends Object>> bindAndApply(
            @Name("instance") @TypeInfo("ceylon.language::Object") Object instance,
            @Name("types") @Sequenced @TypeInfo("ceylon.language::Sequential<ceylon.language.model::Type>") 
            Sequential<? extends ceylon.language.model.Type> types){
        List<com.redhat.ceylon.compiler.typechecker.model.ProducedType> producedTypes = Metamodel.getProducedTypes(types);
        // FIXME: this is wrong because it does not include the container type
        com.redhat.ceylon.compiler.typechecker.model.ProducedType appliedClassType = declaration.getProducedReference(null, producedTypes).getType();
        TypeDescriptor reifiedType = Metamodel.getTypeDescriptorForProducedType(appliedClassType);
        TypeDescriptor reifiedArguments;
        if(declaration.isAnonymous())
            reifiedArguments = TypeDescriptor.NothingType;
        else
            reifiedArguments = Metamodel.getTypeDescriptorForArguments(declaration.getUnit(), (Functional) declaration, appliedClassType);
        return new AppliedClass(reifiedType, reifiedArguments, appliedClassType, instance);
    }

    @Override
    @Ignore
    public TypeDescriptor $getType() {
        return $TypeDescriptor;
    }
}
