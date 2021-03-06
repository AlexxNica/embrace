package com.giddyplanet.embrace.tools.model.webidl;

import java.util.*;

public class Interface implements Definition {
    boolean resolved;
    String name;
    LinkedHashSet<Operation> constructors = new LinkedHashSet<>();
    LinkedHashSet<Operation> operations = new LinkedHashSet<>();
    LinkedHashSet<Attribute> attributes = new LinkedHashSet<>();
    TypeRef superType;
    LinkedHashSet<Interface> interfaces = new LinkedHashSet<>();
    LinkedHashSet<Constant> constants = new LinkedHashSet<>();
    private Set<String> extendedAttributes = new HashSet<>();
    private boolean callback;

    public Interface(String name) {
        this.name = name;
    }

    public String getJavaName() {
        for (String extendedAttribute : extendedAttributes) {
            if (extendedAttribute.startsWith("JavaName=")) {
                String javaName = extendedAttribute.substring(9);
                System.out.println("JavaName for " + name + " is " + javaName);
                return javaName;
            }
        }
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addConstructor(Operation operation) {
        constructors.add(operation);
    }

    public List<Operation> getConstructors() {
        return new ArrayList<>(constructors);
    }

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

    public List<Operation> getOperations() {
        return new ArrayList<>(operations);
    }

    public TypeRef<Interface> getSuperType() {
        return superType;
    }

    public void setSuperType(TypeRef<Interface> superType) {
        this.superType = superType;
    }

    public void addInterface(Interface i) {
        this.interfaces.add(i);
    }

    public LinkedHashSet<Interface> getInterfaces() {
        return interfaces;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public LinkedHashSet<Attribute> getAttributes() {
        return attributes;
    }

    public void addConstant(Constant constant) {
        constants.add(constant);
    }

    public LinkedHashSet<Constant> getConstants() {
        return constants;
    }

    public boolean hasConstructors() {
        return !constructors.isEmpty();
    }

    public boolean isResolved() {
        return resolved;
    }

    void markResolved() {
        this.resolved = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interface that = (Interface) o;

        if (resolved != that.resolved) return false;
        if (!name.equals(that.name)) return false;
        if (!constructors.equals(that.constructors)) return false;
        if (!operations.equals(that.operations)) return false;
        if (!attributes.equals(that.attributes)) return false;
        if (superType != null ? !superType.equals(that.superType) : that.superType != null) return false;
        return interfaces.equals(that.interfaces);

    }

    @Override
    public int hashCode() {
        int result = (resolved ? 1 : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + constructors.hashCode();
        result = 31 * result + operations.hashCode();
        result = 31 * result + attributes.hashCode();
        result = 31 * result + (superType != null ? superType.hashCode() : 0);
        result = 31 * result + interfaces.hashCode();
        return result;
    }

    public void setExtendedAttributes(Set<String> extendedAttributes) {
        this.extendedAttributes = extendedAttributes;
    }

    public Set<String> getExtendedAttributes() {
        return extendedAttributes;
    }

    public void setCallback(boolean callback) {
        this.callback = callback;
        if (this.callback) {
            extendedAttributes.add("MarkedAsCallback");
        }
    }

    public boolean isCallback() {
        return callback;
    }
}
