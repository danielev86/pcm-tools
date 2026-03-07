package com.redcatdev86.service;

import com.redcatdev86.backend.TeamChooserRecord;
import com.redcatdev86.backend.TeamChooserRepository;
import com.redcatdev86.ui.model.TeamChooserRow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TeamChooserService {

    private final TeamChooserRepository repository = new TeamChooserRepository();

    public List<TeamChooserRow> loadRows() {
        return repository.findAll()
                .stream()
                .map(this::convert)
                .sorted((a, b) -> b.getScore().compareTo(a.getScore()))
                .toList();
    }

    private TeamChooserRow convert(TeamChooserRecord record) {
        return new TeamChooserRow(
                safe(record.getTeamName()),
                record.getDivisionId(),
                normalize(record.getDivisionConstant()),
                record.getCountryId(),
                safe(record.getCountryFlag()),
                record.getContinentId(),
                normalize(record.getContinentConstant()),
                randomScore()
        );
    }

    private BigDecimal randomScore() {
        double value = ThreadLocalRandom.current().nextDouble(0.0, 100.0);
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
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