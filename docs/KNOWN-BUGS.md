# Historical Website Defects — Previous JaJuMa Target

> These defects belong to the former `demo.extension.jajuma.de` test target. They are preserved as examples of manual defect reporting and do not apply to the current Adobe Luma suite.

## BUG-001 — Available products are missing from catalog search

- Status: Open
- Area: Catalog search
- Severity: Major
- Environment: `https://demo.extension.jajuma.de/`, desktop Chrome
- Reproducibility: 3/3 queries

### Preconditions

`Proteus Fitness Jackshirt` is visible under Men > Tops > Jackets.

### Steps

1. Open the storefront.
2. Search for `Proteus Fitness Jackshirt`.
3. Repeat with `Fitness` and `Radiant Tee`.

### Expected

Available products matching the title or indexed terms appear in search results.

### Actual

The page displays `Your search returned no results.` Proteus also appears in category navigation, proving that the product exists in the catalog.

### Automation

`StorefrontFeaturesTest.searchReturnsProducts` documents the expected behavior under the `known-defect` group. It is disabled so a confirmed environment defect does not make every portfolio CI run red. Re-enable it after the store search index is repaired.

## BUG-002 — Checkout application renders a blank page

- Status: Open
- Area: Checkout
- Severity: Critical
- Environment: `https://demo.extension.jajuma.de/`, desktop Chrome
- Reproducibility: Repeated with a newly created customer and JaJuMa's published demo customer

### Preconditions

A signed-in customer has `Proteus Fitness Jackshirt` in the shopping cart.

### Steps

1. Open the cart.
2. Confirm that the product and subtotal are displayed.
3. Click `Proceed to Checkout`.

### Expected

The checkout application displays a saved shipping address or a new-address form, shipping methods, and payment flow.

### Actual

The `/checkout/` route loads only the store header and footer. The checkout content area remains blank for more than 60 seconds. No shipping or payment controls appear.

### Automation

The active E2E journey stops after verified cart addition. Checkout and review page-object operations are preserved for reactivation after the third-party demo checkout is repaired.

## BUG-003 — Demo customer login returns a server error

- Status: Intermittent/Open
- Area: Customer authentication
- Severity: Critical
- Environment: `https://demo.extension.jajuma.de/`, desktop Chrome

### Steps

1. Open Customer Login.
2. Enter the demo credentials published by JaJuMa on that page.
3. Submit the form.

### Expected

The demo customer is authenticated and redirected to the account page.

### Actual

Magento displays `There has been an error processing your request` with an internal error-log record number. Previously created accounts have also been temporarily disabled after repeated public-demo runs.

### Automation

The stable active journey uses a guest cart. Login page objects are preserved, but account-dependent E2E execution should be restored only when the shared demo authentication service is stable.
