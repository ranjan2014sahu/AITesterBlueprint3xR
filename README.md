# AI Tester Blueprint 3.x

A practical, project-driven collection for QA engineers learning how to use Large Language Models and prompt engineering in real testing workflows.

This repository combines foundational LLM concepts with prompt engineering, test case generation, and a Selenium framework scaffold built from a prompt-driven design.

## Repository Overview

- `chapter_01_LLM_Basics/`
  - `attention_is_all_you_need.html` — interactive walkthrough of Transformer and attention concepts.
  - `attention_interactive.html` — self-attention visualisation to show how input tokens affect model output.
  - `Notes.md` — summary notes for chapter 1.

- `chapter_02_Prompt_Eng/`
  - `Anti_Hallucinations_Rules.md` — guardrails to keep model output grounded and avoid unsupported assumptions.
  - `Project1_TC_Gen/` — prompt-driven test case generation from API requirements.
    - `RICE-POT-TestCase-Prompt.md` — prompt template for generating test cases.
    - `Restful-booker.pdf` — sample API doc input.
    - `Restful_Booker_API_Test_Cases.md` — generated test cases from the prompt.
    - `output/` — example model output CSV.
  - `Project2_Selenium_Framework/` — Selenium framework scaffold created via prompt engineering.
    - `Problem.md` — project brief.
    - `SKILL.md` — prompt-builder skill definition.
    - `blank-template-rice-pot.md` — fill-in template with prompt structure.
    - `AdvanceSeleniumFramework/` — generated Maven/Selenium project.
  - `templates/` — reusable prompt templates for QA tasks.
    - `01_TestCaseGeneration_Prompt.md`
    - `02_TestCases_from_prd`
    - `03_API_Test_Generation.md`
    - `04_Negative_TC_Only.md`
    - `05_Secuirty_Test.md`
    - `06_Regression_Suite.md`

## What This Repo Contains

### Chapter 1 — LLM Basics

A primer on how LLMs process text and why prompt wording matters.
- Use the HTML files locally in a browser.
- Read `Notes.md` for key concepts.

### Chapter 2 — Prompt Engineering for QA

A practical set of prompts and projects for generating test cases, reducing hallucination, and scaffolding a Selenium automation project.

- `Anti_Hallucinations_Rules.md` explains how to make AI output stay factual.
- `Project1_TC_Gen/` shows how to convert an API document into test cases.
- `Project2_Selenium_Framework/` shows how to prompt an AI to create a Selenium framework.
- `templates/` contains ready-to-use prompt patterns for QA tasks.

## How to Use This Repository

### 1. Explore chapter 1

Open the HTML files in `chapter_01_LLM_Basics/` with your browser.

### 2. Generate test cases from requirements

Open `chapter_02_Prompt_Eng/Project1_TC_Gen/RICE-POT-TestCase-Prompt.md` in an AI tool, attach the sample API document, and use the prompt to generate structured test cases.

### 3. Use prompt templates

Open any file under `chapter_02_Prompt_Eng/templates/`, copy the prompt block, replace placeholders, and run it in your preferred LLM tool.

### 4. Run the Selenium framework

The generated Selenium project is located at:

```powershell
cd chapter_02_Prompt_Eng/Project2_Selenium_Framework/AdvanceSeleniumFramework
mvn -q clean test-compile
mvn test
mvn test -DsuiteXmlFile=testng-smoke.xml
```

## Notes

- The Selenium framework uses Maven and TestNG.
- For Project 2, ensure you have JDK 11+ and Maven 3.9+ installed.

## Suggested Workflow

1. Learn the basics from `chapter_01_LLM_Basics/`.
2. Apply prompt guardrails from `Anti_Hallucinations_Rules.md`.
3. Use prompt templates to generate test cases.
4. Review the generated Selenium project in `Project2_Selenium_Framework/AdvanceSeleniumFramework`.

---

Created to help QA engineers learn practical AI-assisted testing and prompt engineering workflows.