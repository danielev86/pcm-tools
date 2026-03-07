package com.redcatdev86.service;

import com.redcatdev86.backend.TeamBuilderRecord;
import com.redcatdev86.backend.TeamBuilderRepository;
import com.redcatdev86.ui.model.TeamBuilderRow;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TeamBuilderService {

    private final TeamBuilderRepository repository = new TeamBuilderRepository();

    public List<TeamBuilderRow> loadRows() {
        return repository.findAll()
                .stream()
                .map(this::convert)
                .toList();
    }

    public TeamBuilderRow generateSponsor(String continent, String country, String region) {
        List<TeamBuilderRow> filtered = loadRows().stream()
                .filter(row -> matches(continent, row.getContinent()))
                .filter(row -> matches(country, row.getCountry()))
                .filter(row -> matches(region, row.getRegion()))
                .toList();

        if (filtered.isEmpty()) {
            return null;
        }

        int index = ThreadLocalRandom.current().nextInt(filtered.size());
        return filtered.get(index);
    }

    private boolean matches(String selected, String value) {
        return selected == null || selected.isBlank() || "All".equals(selected) || selected.equals(value);
    }

    private TeamBuilderRow convert(TeamBuilderRecord record) {
        return new TeamBuilderRow(
                safe(record.getSponsorName()),
                record.getRegionId(),
                normalize(record.getRegionConstant()),
                record.getCountryId(),
                normalize(record.getCountryConstant()),
                safe(record.getCountryFlag()),
                record.getContinentId(),
                normalize(record.getContinentConstant())
        );
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return "";
        }

        String normalized = value.replace("_", " ").trim().toLowerCase();
        String[] parts = normalized.split("\\s+");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (part.isBlank()) {
                continue;
            }

            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1))
                    .append(" ");
        }

        return builder.toString().trim();
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}