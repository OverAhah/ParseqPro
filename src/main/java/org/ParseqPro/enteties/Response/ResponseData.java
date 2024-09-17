package org.ParseqPro.enteties.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {

    private boolean access_control_allow_credentials;
    private String access_control_allow_origin;
    private String connection;
    private int content_length;
    private String date;
    private String server;
    private String vary;

    private List<GetLists> getLists;
    private Page page;
    private List<Resource> resources;
    private int resourcesTotalNumber;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetLists {
        private String name;
        private List<String> mutations;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Page {
        private int zeroBasedNumber;
        private int size;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Resource {
        private String mutationId;
        private String mutationType;
        private String maybeHgvsGdna;
        private String maybeTrivialName;
        private List<String> analysesTranscripts;
        private List<Analysis> analyses;
        private boolean inAnalysis;
        private double organizationFrequencyRatio;
        private String maybeHighestTier;
        private boolean isAnnotatedByAmp;
        private boolean isAnnotatedByAcmg;
        private List<TherapeuticAnnotation> therapeuticAnnotations;
        private List<DiagnosticAnnotation> diagnosticAnnotations;
        private List<PrognosticAnnotation> prognosticAnnotations;
        private List<LowTierAnnotation> lowTierAnnotations;
        private List<AcmgAnnotation> acmgAnnotations;
        private boolean hasPrivateAnnotations;
        private boolean sentToAnnotation;
        private List<String> drugs;
        private String maybeReferenceGenomeContigId;
        private String maybeChrNumber;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Analysis {
        private String analysisId;
        private String sampleName;
        private String sequencingRunName;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TherapeuticAnnotation {
        private String organizationName;
        private boolean isPublic;
        private String evidenceLevel;
        private List<String> referenceLinks;
        private String tumorType;
        private String drugTradeName;
        private String drugName;
        private String sensitivity;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiagnosticAnnotation {
        private String organizationName;
        private boolean isPublic;
        private String evidenceLevel;
        private List<String> referenceLinks;
        private String tumorType;
        private String tumorSubtype;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PrognosticAnnotation {
        private String organizationName;
        private boolean isPublic;
        private String evidenceLevel;
        private List<String> referenceLinks;
        private String tumorType;
        private String prognosis;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LowTierAnnotation {
        private String organizationName;
        private boolean isPublic;
        private String evidenceLevel;
        private List<String> referenceLinks;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AcmgAnnotation {
        private String organizationName;
        private boolean isPublic;
        private String acmgSignificances;
        private String transcriptId;
    }
}