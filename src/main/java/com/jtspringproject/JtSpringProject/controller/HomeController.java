package com.jtspringproject.JtSpringProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Welcome to the API</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            text-align: center;
                            background-color: #f9f9f9;
                            margin: 0;
                            padding: 0;
                        }
                        header {
                            padding: 50px;
                            background-color: #4CAF50;
                            color: white;
                        }
                        img {
                            max-width: 100%;
                            height: auto;
                            margin: 20px 0;
                        }
                        .description {
                            font-size: 18px;
                            margin: 20px;
                            color: #333;
                        }
                    </style>
                </head>
                <body>
                    <header>
                        <h1>Welcome to the API</h1>
                    </header>
                    <div class="description">
                        <p>Explore the power of our API to transform your applications.
                        This is a simple demo to show how a REST API can serve a web page.</p>
                    </div>
                    <img src="https://i.imgur.com/ctpOniL.png" alt="Welcome Image">
                </body>
                </html>
                """;
    }
}

