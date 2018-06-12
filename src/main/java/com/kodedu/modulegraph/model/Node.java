package com.kodedu.modulegraph.model;

import java.util.Objects;

public class Node {
    private String id;
    private String label;

    public Node(String moduleName) {
        this.id = moduleName;
        this.label = moduleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id) &&
                Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, label);
    }
}