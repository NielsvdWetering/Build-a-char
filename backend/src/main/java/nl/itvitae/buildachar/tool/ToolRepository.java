package nl.itvitae.buildachar.tool;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToolRepository extends JpaRepository<Tool, UUID> {}
