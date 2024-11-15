package com.example.demo.util.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;
    
    public ResourceNotFoundException()
    {
        super("Resource not found");
    }
}