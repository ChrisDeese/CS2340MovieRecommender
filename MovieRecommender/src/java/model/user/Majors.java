package model.user;

/**
 * @author jacob
 */
public enum Majors {
    NONE (0), AEROSPACE (1), APP_LANGUAGES (2), APP_MATH (3), APP_PHYS (4),
        ARCHITECTURE (5), BIOCHEMISTRY (6), BIOLOGY (7), BIOMEDICAL_ENG (8),
        BUSINESS (9), CHEMICAL_ENG (10), CHEMISTRY (11), CIVIL_ENG (12),
        COMP_MEDIA (13), COMP_ENG (14), COMPUTER_SCIENCE (15),
        DISCRETE_MATH (16), EARTH_SCIENCES (17), ECONOMICS (18),
        ECON_INT_AFFAIRS (19), ELECTRICAL_ENG (20), ENVIRON_ENG (21),
        GLOBAL_ECON (22), HISTORY (23), INDUSTRIAL_DESIGN (24),
        INDUSTRIAL_ENG (25), INT_AFFAIRS (26), LANG_INT_AFFAIRS (27),
        LITERATURE (28), MATERIAL_ENG (29), MECHANICAL_ENG (30),
        NUCLEAR_ENG (31), PHYSICS (32), PSYCHOLOGY (33), PUBLIC_POLICY (34);

    private final int majorID;

    private Majors(int majorID) {
        this.majorID = majorID;
    }
}

/*
In order:

0. No major (used for minor)
1. Aerospace Engineering
2. Applied Languages and Intercultural Studies
3. Applied Mathematics
4. Applied Physics
5. Architecture
6. Biochemistry
7. Biology
8. Biomedical Engineering
9. Business Administration
10. Chemical and Biomolecular Engineering
11. Chemistry
12. Civil Engineering
13. Computational Media
14. Computer Engineering
15. Computer Science
16. Discrete Mathematics
17. Earth and Atmospheric Sciences
18. Economics
19. Economics and International Affairs
20. Electrical Engineering
21. Environmental Engineering
22. Global Economics and Modern Languages
23. History, Technology, and Society
24. Industrial Design
25. Industrial Engineering
26. International Affairs
27. International Affairs and Modern Language
28. Literature, Media, and Communication
29. Materials Science and Engineering
30. Mechanical Engineering
31. Nuclear and Radiological Engineering
32. Physics
33. Psychology
34. Public Policy
*/
