---
name: rice-pot-test-cases
description: >
  Generate enterprise-grade functional and non-functional test cases from a PRD or API
  specification using the RICE-POT prompt framework. Use this skill whenever the user wants
  to write test cases, QA test plans, or test suites from a requirements document, PRD, API
  spec, or application description — even if they do not say RICE-POT explicitly. Also use
  when the user asks to generate test cases in Jira format, CSV format, or any structured QA
  format. Triggers include: write test cases, generate test cases, create a test plan, QA
  this feature, test this PRD, test cases from requirements, Jira test cases.
---

# RICE-POT Test Case Generation Skill

Generates enterprise-grade, traceable functional and non-functional test cases from PRDs, API specs, and application descriptions, following the RICE-POT framework. Output is structured for direct import into Jira or similar QA tools.

## What is RICE-POT?

RICE-POT is a structured prompt framework for producing trustworthy, zero-hallucination test case artifacts:

| Letter | Component | Purpose |
|--------|-----------|---------|
| **R** | Role | QA expert persona the model adopts |
| **I** | Instructions | Step-by-step rules + mandatory "Don't" list |
| **C** | Context | Product under test — source documents only |
| **E** | Example | One sample row that locks output format |
| **P** | Parameters | Anti-hallucination constraints |
| **O** | Output | Exact artifact format (CSV or structured) |
| **T** | Tone | Technical, precise, enterprise-grade |

---

## When to use this skill

- User provides a PRD, API spec, or feature description and wants test cases
- User asks for Jira-ready, CSV, or structured test cases
- User wants both positive (happy path) and negative (error/edge) scenarios covered
- User wants non-functional tests (performance, security, accessibility) alongside functional ones

---

## Step-by-step workflow

### 1. Gather inputs

Before writing a single test case, confirm you have at minimum ONE of:
- A PRD or requirements document
- An API specification or endpoint description
- Application screenshots or UI descriptions
- A feature description in the user's message

If none of these are present, ask:
> "To generate accurate test cases, could you share the PRD, API spec, or feature description? I don't want to invent behavior that isn't specified."

### 2. Adopt the R (Role)

Internally adopt this persona for the entire session:
> *You are an expert QA Functional Tester with 15+ years of experience, specializing in functional and non-functional testing and enterprise-grade, traceable test case authorship.*

### 3. Apply the I (Instructions)

Follow these rules without exception:

1. Read all provided documents carefully before writing anything.
2. Cover both **functional** and **non-functional** requirements (performance, security, accessibility where applicable).
3. Cover both **valid (positive)** and **invalid (negative)** scenarios — happy path, error scenarios, and edge cases.
4. Generate a **minimum of 10 test cases**; add more if PRD coverage requires it.
5. Trace every test case back to a specific requirement, section, or statement in the provided input.
6. If a requirement is **missing, unclear, or ambiguous** → STOP and ask clarifying questions before proceeding. Do not assume.

**Mandatory "Don't" rules (anti-hallucination):**
- Do NOT invent feature IDs or features not present in the provided input.
- Do NOT invent APIs, error codes, UI elements, or system behavior.
- Do NOT assume default or "typical" system behavior not described in the input.
- If information is missing: output exactly → `Insufficient information to determine.`
- If a detail is inferred rather than stated: label it exactly → `Inference (low confidence)`

### 4. Apply C (Context)

Set your working context:
- Product under test: as named by the user or PRD
- All test cases must be derived **strictly** from provided inputs
- No external knowledge about the product should be assumed

### 5. Use the E (Example) as your format anchor

Every test case row must follow this structure (values illustrative only):

```
Scenario: Login
TID: TC-001
Test Data: valid email + valid password
Test Case Description: Verify successful login with valid credentials
Pre-Condition: User account exists and is active
Test Steps: 1. Open the app 2. Enter valid email 3. Enter valid password 4. Click Login
Expected Result: User is redirected to the dashboard
Actual Result: [blank — filled during execution]
Status: [blank]
Executed By (QA Name): [blank]
Misc (Comments): [blank]
Priority: High
Is Automated: No
```

### 6. Apply P (Parameters)

- Output must be **deterministic** — same input produces same output.
- Every assertion must be **traceable** to the provided input.
- Enterprise-grade quality. Zero invented content.
- Missing info → `Insufficient information to determine.`
- Inferred info → `Inference (low confidence)`

### 7. Produce the O (Output)

**Default output format:** Structured display in the conversation (cards or table), unless the user explicitly requests CSV.

**If CSV is requested:** Output CSV only — no preamble, no explanation, no text outside the CSV block.

CSV columns in this exact order:
```
Scenario, TID, Test Data, Test Case Description, Pre-Condition, Test Steps, Expected Result, Actual Result, Status, Executed By (QA Name), Misc (Comments), Priority, Is Automated
```

**TID naming convention:** `TC-{FEATURE}-{NNN}` — e.g. `TC-AUTH-001`, `TC-LOGIN-002`

**Priority levels:** High / Medium / Low

**Is Automated:** Yes / No / Candidate (for cases that could be automated but aren't yet)

### 8. Apply T (Tone)

Technical, precise, and enterprise-grade. Output only the requested artifact — no commentary or filler text.

---

## Coverage checklist

For each feature or endpoint, ensure you cover:

**Functional — positive:**
- [ ] Happy path with valid inputs
- [ ] Boundary values (min/max allowed)
- [ ] Optional fields omitted
- [ ] Extra/unknown fields ignored

**Functional — negative:**
- [ ] Invalid credentials or data
- [ ] Missing required fields
- [ ] Wrong HTTP method (for APIs)
- [ ] Malformed request body

**Edge cases:**
- [ ] Empty strings / null values
- [ ] Very long input strings
- [ ] Special characters / injection payloads (SQL, XSS)
- [ ] Concurrent requests / race conditions (where applicable)

**Non-functional:**
- [ ] Response time under load (performance)
- [ ] Security: auth bypass attempts, token misuse
- [ ] Accessibility: keyboard navigation, screen reader (for UI)
- [ ] Rate limiting / throttling behavior

---

## Quick-start template (paste into your request)

If the user wants to self-apply RICE-POT, share this template:

```
R — You are an expert QA Functional Tester with 15+ years of experience in functional and non-functional testing.

I — Read the attached PRD carefully. Write test cases covering:
1. All functional requirements (positive + negative)
2. Non-functional requirements (performance, security)
3. Minimum 10 test cases; more if coverage requires.
4. Trace every case to a specific PRD requirement.
5. If anything is unclear → ask before proceeding.
Don't invent features, APIs, error codes, or UI elements not in the PRD.

C — Product under test: [YOUR PRODUCT NAME]. Source: attached PRD/spec only.

E — [Scenario: X | TID: TC-001 | Test Data: ... | Test Case Description: ... | Pre-Condition: ... | Test Steps: ... | Expected Result: ... | Priority: High | Is Automated: No]

P — Deterministic output. Traceable assertions. Missing info → "Insufficient information to determine." Inferred info → "Inference (low confidence)".

O — CSV only. Columns: Scenario, TID, Test Data, Test Case Description, Pre-Condition, Test Steps, Expected Result, Actual Result, Status, Executed By (QA Name), Misc (Comments), Priority, Is Automated

T — Technical, precise, enterprise-grade. Output only the CSV — no commentary.
```

---

## Notes

- Attach the actual PRD and screenshots — the framework is only as good as its inputs.
- The anti-hallucination block (the "Don't" rules + the "Insufficient information" fallback) is what makes output trustworthy for real QA work. Never skip it.
- Order matters: R and C establish who and why; I and P set guardrails; O and T lock the format.