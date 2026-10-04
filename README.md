# Adobe Luma Selenium QA Portfolio

[![Smoke tests](https://github.com/fatmamosleh/automation-testing-project/actions/workflows/smoke-tests.yml/badge.svg)](https://github.com/fatmamosleh/automation-testing-project/actions/workflows/smoke-tests.yml)
[![End-to-end test](https://github.com/fatmamosleh/automation-testing-project/actions/workflows/e2e-tests.yml/badge.svg)](https://github.com/fatmamosleh/automation-testing-project/actions/workflows/e2e-tests.yml)

Java Selenium/TestNG automation for the [Adobe Luma demo store](https://luma.enablementadobe.com/). The project demonstrates Page Object Model design, reusable browser configuration, generated test data, explicit waits, TestNG groups, failure screenshots, Allure results, and GitHub Actions.

## Automated coverage

### Smoke suite

- Verify homepage navigation, authentication entry point, and shopping cart
- Open the Men category and filter Jackets
- Verify a known product is returned by the filter
- Sort products by ascending price and validate the complete price order
- Open a selected product and verify its title

### End-to-end suite

1. Generate a unique customer email for the current run
2. Register the customer
3. Sign out and sign back in with the generated credentials
4. Open Men and filter Jackets
5. Sort by ascending price
6. Open `Proteus Fitness Jackshirt`
7. Select size `M` and color `Orange`
8. Add the product to the cart and verify its options
9. Complete shipping, payment, and review
10. Place the order and verify the generated order number
11. Open My Orders and confirm that the order was saved

The Adobe demo stores customers, carts, and orders in browser `localStorage`; it has no production database. `TestDataFactory` therefore creates isolated customer data for every E2E run.

## Technology

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model
- Allure results
- GitHub Actions

## Project structure

```text
src/main/java/Pages/       Page objects
src/test/java/support/     Browser lifecycle, data factory, screenshots
src/test/java/             TestNG scenarios
docs/                      QA documentation
.github/workflows/         Headless smoke-test CI
TestSuit.xml               Ordered regression suite
```

## Run from IntelliJ Terminal

Full suite:

```powershell
& "C:\Program Files\Apache\Maven\bin\mvn.cmd" clean test "-Dluma.headless=false"
```

Smoke tests:

```powershell
& "C:\Program Files\Apache\Maven\bin\mvn.cmd" clean test "-Dgroups=smoke" "-Dluma.headless=true"
```

End-to-end test:

```powershell
& "C:\Program Files\Apache\Maven\bin\mvn.cmd" clean test "-Dgroups=e2e" "-Dluma.headless=false"
```

## Results

- TestNG HTML: `target/surefire-reports/index.html`
- Allure results: `target/allure-results/`
- Failure screenshots: `target/failure-screenshots/`

## Test groups

| Group | Purpose | Execution |
|---|---|---|
| `smoke` | Fast storefront confidence checks | Local and GitHub Actions |
| `e2e` | Generated customer through verified order history | Local/manual |

## Notes

- Test payment details are dummy values accepted only by this educational demo.
- Generated customers are isolated to the Chrome profile used during a run.
- The E2E scenario intentionally keeps registration, login, checkout, and order verification in one browser session so `localStorage` remains available.
