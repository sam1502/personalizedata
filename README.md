[![CircleCI](https://dl.circleci.com/status-badge/img/gh/sam1502/personalizedata/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/sam1502/personalizedata/tree/master)


#Personalized data API

This project has API which provides personalized information for shoppers and product metadata, serving both internal data team operations and external eCommerce integration.

## Technologies

- Java
- Spring Boot 3.1.10
- Spring Data JPA
- Spring Web
- PostgresSQL
- Lombok
- Docker
- Render - deploying docker image
- Codiga - code coverage
- CircleCI - CI/CD

## Endpoints

### 1. Internal Data Team Operations

#### Save Shopper Personlaised Product List
- **Endpoint:** `POST /api/shoppers`
- **Description:** Saves personalized product lists for shoppers.
- **Request Body:**
  ```json
  {
    "shopperId": "S-1000",
    "shelf": [
      {
        "productId": "MB-2093193398",
        "relevancyScore": 31.089209569320897
      },
      {
        "productId": "BB-2144746855",
        "relevancyScore": 55.16626010671777
      },
      {
        "productId": "MD-543564697",
        "relevancyScore": 73.01492966268303
      }
    ]
  }

## Save Product Metadata

- **Endpoint:** `POST /api/product-metadata`
- **Description:** Saves metadata for products.
- **Request Body:**
  ```json
  {
    "productId": "MD-543564697",
    "category": "Adult",
    "brand": "Adidas"
  }

### 2. External eCommerce API

#### Retrieve Shopper Products - Paginated

- **Endpoint:** `GET /api/external/shoppers/{shopperId}/products`
- **Description:** Retrieves personalized product information by shopper with optional filtering by category, brand, and pagination support.
- **Parameters:**
  - `{shopperId}`: The unique identifier of the shopper.
  - `category` (optional): Filter products by category.
  - `brand` (optional): Filter products by brand.
  - `page` (optional, default = 1): Page number for pagination.
  - `size` (optional, default = 10, max = 100): Number of items per page.
- **Example Request:**
  ```bash
  curl -X GET \
    'http://localhost:8080/api/external/shoppers/S-1000/products?category=Adult&brand=Adidas&page=1&size=10'

- **Response:** Returns a paginated list of personalized product information for the specified shopper, filtered by category and brand.
