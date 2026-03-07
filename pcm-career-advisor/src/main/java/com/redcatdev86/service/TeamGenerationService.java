package com.redcatdev86.service;

import com.redcatdev86.backend.CyclistRecord;
import com.redcatdev86.backend.CyclistRepository;
import com.redcatdev86.ui.model.CyclistRow;
import com.redcatdev86.ui.model.GeneratedTeamResult;
import com.redcatdev86.ui.model.TeamBuilderRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TeamGenerationService {

    private final CyclistRepository cyclistRepository = new CyclistRepository();

    public GeneratedTeamResult generateTeam(TeamBuilderRow sponsor) {
        String teamType = randomTeamType();
        int rosterSize = randomRosterSize(teamType);

        List<CyclistRow> allCyclists = cyclistRepository.findAll()
                .stream()
                .map(this::toRow)
                .toList();

        List<CyclistRow> roster = switch (teamType) {
            case "World Tour" -> generateWorldTourRoster(allCyclists, sponsor, rosterSize);
            case "Professional" -> generateProfessionalRoster(allCyclists, sponsor, rosterSize);
            default -> generateContinentalRoster(allCyclists, sponsor, rosterSize);
        };

        return new GeneratedTeamResult(sponsor, teamType, rosterSize, roster);
    }

    private List<CyclistRow> generateWorldTourRoster(List<CyclistRow> allCyclists,
                                                     TeamBuilderRow sponsor,
                                                     int rosterSize) {

        List<CyclistRow> sameCountry = shuffled(allCyclists.stream()
                .filter(c -> sponsor.getCountry().equals(c.getCountry()))
                .toList());

        List<CyclistRow> otherCountries = shuffled(allCyclists.stream()
                .filter(c -> !sponsor.getCountry().equals(c.getCountry()))
                .toList());

        List<CyclistRow> result = new ArrayList<>();

        int maxSameCountry = Math.min(5, rosterSize);

        addUpTo(result, sameCountry, maxSameCountry);
        addUpTo(result, otherCountries, rosterSize - result.size());

        if (result.size() < rosterSize) {
            addUpToAvoidDuplicates(result, sameCountry, rosterSize - result.size());
        }

        return result;
    }

    private List<CyclistRow> generateProfessionalRoster(List<CyclistRow> allCyclists,
                                                        TeamBuilderRow sponsor,
                                                        int rosterSize) {

        List<CyclistRow> sameCountry = shuffled(allCyclists.stream()
                .filter(c -> sponsor.getCountry().equals(c.getCountry()))
                .toList());

        List<CyclistRow> otherCountries = shuffled(allCyclists.stream()
                .filter(c -> !sponsor.getCountry().equals(c.getCountry()))
                .toList());

        List<CyclistRow> result = new ArrayList<>();

        int maxSameCountry = Math.min(10, rosterSize);

        addUpTo(result, sameCountry, maxSameCountry);
        addUpTo(result, otherCountries, rosterSize - result.size());

        if (result.size() < rosterSize) {
            addUpToAvoidDuplicates(result, sameCountry, rosterSize - result.size());
        }

        return result;
    }

    private List<CyclistRow> generateContinentalRoster(List<CyclistRow> allCyclists,
                                                       TeamBuilderRow sponsor,
                                                       int rosterSize) {

        List<CyclistRow> sameCountry = shuffled(allCyclists.stream()
                .filter(c -> sponsor.getCountry().equals(c.getCountry()))
                .toList());

        List<CyclistRow> sameContinentOtherCountry = shuffled(allCyclists.stream()
                .filter(c -> sponsor.getContinent().equals(c.getContinent()))
                .filter(c -> !sponsor.getCountry().equals(c.getCountry()))
                .toList());

        List<CyclistRow> result = new ArrayList<>();

        addUpTo(result, sameCountry, rosterSize);

        if (result.size() < rosterSize) {
            addUpToAvoidDuplicates(result, sameContinentOtherCountry, rosterSize - result.size());
        }

        return result;
    }

    private void addUpTo(List<CyclistRow> target, List<CyclistRow> source, int amount) {
        int maxSize = target.size() + amount;

        for (CyclistRow cyclist : source) {
            if (target.size() >= maxSize) {
                return;
            }

            if (!containsCyclist(target, cyclist)) {
                target.add(cyclist);
            }
        }
    }

    private void addUpToAvoidDuplicates(List<CyclistRow> target, List<CyclistRow> source, int amount) {
        int maxSize = target.size() + amount;

        for (CyclistRow cyclist : source) {
            if (target.size() >= maxSize) {
                return;
            }

            if (!containsCyclist(target, cyclist)) {
                target.add(cyclist);
            }
        }
    }

    private boolean containsCyclist(List<CyclistRow> list, CyclistRow candidate) {
        return list.stream().anyMatch(c ->
                safe(c.getFirstName()).equals(safe(candidate.getFirstName()))
                        && safe(c.getLastName()).equals(safe(candidate.getLastName()))
                        && safe(c.getRegion()).equals(safe(candidate.getRegion()))
                        && safe(c.getCountry()).equals(safe(candidate.getCountry()))
        );
    }

    private List<CyclistRow> shuffled(List<CyclistRow> input) {
        List<CyclistRow> copy = new ArrayList<>(input);
        Collections.shuffle(copy);
        return copy;
    }

    private CyclistRow toRow(CyclistRecord record) {
        return new CyclistRow(
                safe(record.getFirstName()),
                safe(record.getLastName()),
                normalize(record.getRegion()),
                safe(record.getCountry()),
                normalize(record.getContinent())
        );
    }

    private String randomTeamType() {
        String[] values = {"World Tour", "Professional", "Continental"};
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    private int randomRosterSize(String teamType) {
        return switch (teamType) {
            case "World Tour" -> ThreadLocalRandom.current().nextInt(25, 31);
            case "Professional" -> ThreadLocalRandom.current().nextInt(16, 25);
            default -> ThreadLocalRandom.current().nextInt(10, 16);
        };
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