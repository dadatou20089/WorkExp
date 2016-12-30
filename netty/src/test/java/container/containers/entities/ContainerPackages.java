package container.containers.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerPackages {
    private List<String> packages = new ArrayList<>();
    public void addPackage(String packageName) {
        this.packages.add(packageName);
    }
}
