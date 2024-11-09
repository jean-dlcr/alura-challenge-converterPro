# Converter Pro

It is an educational desktop application built in Java for currency conversion using a free [exchange API](https://www.exchangerate-api.com/docs/overview). The application requires an API_KEY to access exchange rates and logs each successful conversion in a JSON history file. It also automatically sets the language and downloads country flags to enhance the user experience.

## Features

* Currency Conversion: Converts between multiple currencies using rates from a free API.
* API Key: Requires a valid [API_KEY](https://www.exchangerate-api.com/docs/overview) to access data.
* Conversion History: Logs each conversion in a JSON file, viewable in a history table.
* Language Selection: Supports multiple languages with YML files (default in Spanish).
* Country Flags: Downloads and displays flags for each currency in the selection menu.
* User Assistance: Includes a Help tab and an About section with educational links.

## Technical Specifications.

* Platform: Desktop application.
* Programming Language: Java 11 or higher.
* Build Tool: Maven.
* Interface: Fixed-size window.
* Dependencies: Requires an internet connection for API access and resource downloads.

## Installation
1. Download the JAR file from the [Releases](https://github.com/jean-dlcr/alura-challenge-converterPro/releases) section of the GitHub repository.
2. Recommendation: Place the JAR file in a **new empty folder**. This is because the app will automatically generate configuration and resource files in the same directory as the JAR, so it’s best if no other files are present.
3. Internet Connection: A stable internet connection is recommended at all times, as the app requires internet access to download country flags and retrieve exchange rate data from the API.
4. You can also clone the repository using the following command:

```
git clone https://github.com/jean-dlcr/alura-challenge-converterPro.git
```

## Initial Setup

* Entering the API Key: You will need to enter a valid API Key to proceed. If you do not have an API Key, you can obtain one for free by registering on the API provider's website.

* Splash Screen: After selecting the language, a splash screen will appear while the application connects to the API. During this time, it will download country flags for the currencies available through the API.

* Language Selection: Upon launching the application, you will be prompted to select a language. The available languages are loaded from YML files located in the config/languages/ folder. If no YML files are present, the application will default to Spanish.

* API Key Link: For more information on how to obtain your API Key and to access the API documentation, visit [API Documentation](https://www.exchangerate-api.com/docs/overview).

## Usage

### Conversion Screen

This main panel is divided into sections:

1. Currency Selection and Input: Choose the source currency from the top dropdown and enter the amount to convert in the adjacent input box. While labels like "From" and "To" are not present, the top dropdown represents the source currency.

2. Swap Button: Click this button to switch the selected currencies without altering the entered amount. For example, "USD - 100" converting to "PEN" changes to "PEN - 100" converting to "USD."

3. Result and Target Currency: This section includes a non-editable result field and a dropdown to choose the target currency. If the source and target are the same, the app shows a 1:1 result without contacting the API.

4. Convert Button: Located at the bottom, clicking this button validates the input and requests the conversion rate from the API. The calculated result is displayed in the output field. Additionally, the conversion data is saved to a JSON file, which is used to populate the "History" panel.

### History Panel

The history tab displays all previous conversions in a table format, showing details such as the date, source currency, amount, target currency, and result. This log is generated from a JSON file where successful conversions are stored.

### Help

This panel provides a straightforward, four-step guide for using the app:

1. Select a source currency.
2. Enter the amount to convert.
3. Select a target currency.
4. Click "Convert" to process the exchange.

Note: The swap button exchanges the source and target currencies.