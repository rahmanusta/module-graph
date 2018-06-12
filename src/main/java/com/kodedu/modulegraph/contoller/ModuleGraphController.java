package com.kodedu.modulegraph.contoller;

import com.kodedu.modulegraph.model.Edge;
import com.kodedu.modulegraph.model.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.module.ModuleDescriptor.Requires;

@RestController
public class ModuleGraphController {

    @GetMapping("/modules")
    public Map<String, HashSet<?>> moduleInfo() {
        var nodes = new HashSet<Node>();
        var edges = new HashSet<Edge>();

        fillNodeAndEdges(nodes, edges);

        return Map.of("nodes", nodes, "edges", edges);
    }

    private void fillNodeAndEdges(HashSet<Node> nodes, HashSet<Edge> edges) {
        Set<Module> modules = ModuleLayer.boot().modules();
        for (Module module : modules) {
            String moduleName = module.getName();

            if (moduleNotContain(moduleName, "jdk")) {
                nodes.add(new Node(moduleName));
            }

            Set<Requires> requires = module.getDescriptor().requires();
            for (Requires require : requires) {
                edges.add(new Edge(moduleName, require.name()));
            }
        }
    }

    private boolean moduleNotContain(String moduleName, String text) {
        return !moduleName.startsWith(text);
    }
}