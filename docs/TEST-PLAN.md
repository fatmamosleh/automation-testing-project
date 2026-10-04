# Test Plan

## Objective

Validate the critical customer journeys of the Adobe Luma educational storefront on desktop Chrome.

## Scope

- Homepage and primary navigation
- Men category filtering and product sorting
- Product details and configurable options
- Unique customer registration, logout, and login
- Shopping cart verification
- Shipping, payment, review, and order placement
- Account order-history verification

## Test levels

- `smoke`: fast storefront checks suitable for every pull request
- `e2e`: state-changing customer purchase journey run locally

## Environment

- Java 17+
- Maven 3.9+
- Current Google Chrome and Selenium Manager
- Default URL: `https://luma.enablementadobe.com/`

## Entry criteria

- Demo store is reachable
- Product catalog JavaScript loads successfully
- `Proteus Fitness Jackshirt` is available under Men > Jackets

## Exit criteria

- Four smoke tests pass
- The E2E customer journey passes
- Failure screenshots and TestNG/Allure results are reviewed

## Data strategy

The demo uses browser `localStorage`, not a backend database. Every E2E run generates a unique email through `TestDataFactory`, keeps all customer operations in one browser session, and uses dummy payment data accepted by the demo.

## Risks

- The public educational site can change without notice
- Browser storage is isolated to the current Chrome profile
- Third-party Adobe scripts can affect page-loading time
