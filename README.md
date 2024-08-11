## Project Overview: Can I Eat?

**"Can I Eat?"** is a web application specifically designed for pregnant women, helping them determine if certain foods are safe to consume. Utilizing camera-based OCR (Optical Character Recognition), the app scans food labels or packaging and cross-references the ingredients against a comprehensive database of safe and unsafe foods for pregnancy.

### Key Features:
- **Camera OCR Integration:** Quickly scan food labels or packaging to identify ingredients.
- **Safety Database:** Access to a curated list of foods deemed safe or unsafe for pregnant women.
- **Chatbot Assistance:** Get instant answers to food safety questions via an integrated chatbot.
- **User-Friendly Interface:** Designed with ease of use in mind, ensuring quick and reliable information at your fingertips.

This service empowers pregnant women to make informed dietary choices with confidence.


## Technologies Used
- BackEnd
  - Java 17, Spring Boot, Gradle
  - Postgre SQL, AWS S3
  - nglok
  - Github Actions, Docker
  - Google Cloud Vision
- FrontEnd
  - React  
  - GPT-4o mini OpneAI
  - Netlify


## Setup Instructions

1. **Clone the repository**
 ```bash
 git clone https://github.com/Junction-DINO/BE.git
 cd BE
 ```
2. **Build the project**

  ```bash
  ./gradlew build
  ```

3. **Run the application**
  ```bash
  ./gradlew bootRun
  ```

4. **Docker Setup**
  ```bash
  docker build -t junction-backend .
  docker run -p 8080:8080 junction-backend
  ```

## Contact

For any questions or feedback, please reach out to the contributors:

- [ddr4869 (Tom)](https://github.com/ddr4869)
- [sor999 (HyunJe Park)](https://github.com/sor999)
