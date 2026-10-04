# Test Cases

| ID | Scenario | Expected result | Group |
|---|---|---|---|
| SF-001 | Open homepage | Navigation, Sign In, and cart are visible | Smoke |
| SF-002 | Filter Men products by Jackets | Jacket products appear, including Proteus | Smoke |
| SF-003 | Sort filtered products low to high | Every displayed price is in ascending order | Smoke |
| SF-004 | Open Proteus Fitness Jackshirt | Product title matches the selected product | Smoke |
| E2E-001 | Register a generated customer | Customer is created and automatically signed in | E2E |
| E2E-002 | Sign out and sign back in | Generated credentials authenticate successfully | E2E |
| E2E-003 | Filter, sort, and configure Proteus | Size M and Orange are selected | E2E |
| E2E-004 | Add configured product to cart | Product, size, and color are correct | E2E |
| E2E-005 | Complete shipping and payment | Review step appears | E2E |
| E2E-006 | Place order | Confirmation displays an `ORD` number | E2E |
| E2E-007 | Open My Orders | The generated order appears in order history | E2E |
