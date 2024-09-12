package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.exception.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageDataException;
import com.example.exception.MessageNotFoundException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) throws InvalidMessageDataException, AccountNotFoundException {
        if (message.getMessageText() == null || message.getMessageText().trim().isEmpty() || message.getMessageText().length() > 255) {
            throw new InvalidMessageDataException("Message text cannot be empty or cannot exceed 255 characters");
        }
    
        Optional<Account> accountOpt = accountRepository.findById(message.getPostedBy());
        if (!accountOpt.isPresent()) {
            throw new AccountNotFoundException("Account not found for ID: " + message.getPostedBy());
        } 
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElseThrow(() -> new MessageNotFoundException("Message not found for ID: " + messageId));
    }

    public boolean deleteMessage(Integer messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new MessageNotFoundException("Message not found for ID: " + messageId));
        messageRepository.delete(message);
        return true;
    }

    public Message updateMessage(int messageId, String newMessageText) {
        if (newMessageText == null || newMessageText.trim().isEmpty()) {
            throw new IllegalArgumentException("Message text must not be empty");
        }

        if (newMessageText.length() > 255) {
            throw new IllegalArgumentException("Message text must be under 255 characters.");
        }

        Message message = messageRepository.findById(messageId).orElseThrow(() -> new MessageNotFoundException("Message not found for ID: " + messageId));
        message.setMessageText(newMessageText);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByUser(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}