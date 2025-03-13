/* eslint-disable @typescript-eslint/no-explicit-any */
"use client";

import { useState, useEffect, useRef } from "react";
import Image from "next/image";
import EmojiPicker, { EmojiClickData } from "emoji-picker-react";
import { Send, Paperclip, Smile, X, MessageSquare } from "lucide-react";
import "tailwindcss/tailwind.css";

interface Message {
  role: "user" | "bot";
  text: string;
  images?: string[];
}

const API_KEY = "AIzaSyBA8HLmozhNYzV2Xx1989krtBjnMY3NIhM";
const API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";


export default function Chatbot() {
  const [messages, setMessages] = useState<Message[]>([]);
  const [input, setInput] = useState("");
  const [showEmojiPicker, setShowEmojiPicker] = useState(false);
  const [files, setFiles] = useState<File[]>([]);
  const [previewImages, setPreviewImages] = useState<string[]>([]);
  const [isChatOpen, setIsChatOpen] = useState(false);
  const chatEndRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleSendMessage = async () => {
    if (!input.trim() && files.length === 0) return;

    // Always display the user's message first
    const newMessage: Message = { role: "user", text: input, images: previewImages };
    setMessages((prev) => [...prev, newMessage]);

    setInput("");
    setFiles([]);
    setPreviewImages([]);

    // Check if the question is unrelated to bicycles
    const forbiddenWords = ["car", "phone", "computer", "food", "house", "gadget"];
    if (forbiddenWords.some((word) => input.toLowerCase().includes(word))) {
      setMessages((prev) => [...prev, { role: "bot", text: "Sorry, I can only provide advice on bicycles." }]);
      return;
    }

    const botReply: Message = { role: "bot", text: "Processing..." };
    setMessages((prev) => [...prev, botReply]);

    try {
      const response = await fetch(`${API_URL}?key=${API_KEY}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          contents: [{ parts: [{ text: `You are an AI assistant that only answers in English. Please reply only in English. ${input}` }] }],
        }),
      });

      const data = await response.json();
      const botMessage = data?.candidates?.[0]?.content?.parts?.[0]?.text || "Error: No response from AI.";

      setMessages((prev) => [...prev.slice(0, -1), { role: "bot", text: botMessage }]);
    } catch (error) {
      console.error("Error fetching response:", error);
      setMessages((prev) => [...prev.slice(0, -1), { role: "bot", text: "Error: Unable to get response." }]);
    }
  };

  const handleEmojiSelect = (emoji: EmojiClickData) => {
    setInput((prev) => prev + emoji.emoji);
    setShowEmojiPicker(false);
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      const selectedFiles = Array.from(e.target.files);
      setFiles(prev => [...prev, ...selectedFiles]);
      setPreviewImages(prev => [...prev, ...selectedFiles.map(file => URL.createObjectURL(file))]);
    }
  };

  const removeImage = (index: number) => {
    setPreviewImages(prev => prev.filter((_, i) => i !== index));
    setFiles(prev => prev.filter((_, i) => i !== index));
  };

  return (
    <div className="fixed bottom-6 right-6 z-50">
      {!isChatOpen ? (
        <button onClick={() => setIsChatOpen(true)} className="bg-indigo-600 p-4 rounded-full shadow-lg">
          <MessageSquare className="text-white" size={24} />
        </button>
      ) : (
        <div className="w-80 bg-white rounded-xl shadow-lg overflow-hidden">
          <div className="bg-indigo-600 text-white p-4 flex justify-between items-center">
            <h3 className="font-bold">Chatbot</h3>
            <button onClick={() => setIsChatOpen(false)} className="text-white">
              <X size={20} />
            </button>
          </div>
          <div className="p-4 h-96 overflow-y-auto">
            {messages.map((msg, index) => (
              <div key={index} className={`mb-3 flex ${msg.role === "user" ? "justify-end" : "justify-start"}`}>
                <div className={`p-3 rounded-lg ${msg.role === "user" ? "bg-indigo-500 text-white" : "bg-gray-200"}`}>
                  {msg.text}
                  {msg.images && msg.images.map((img, imgIndex) => (
                    <div key={imgIndex} className="relative inline-block">
                      <Image src={img} alt="Uploaded" width={100} height={100} className="mt-2 rounded-lg" />
                      <button onClick={() => removeImage(imgIndex)} className="absolute -top-1 -right-1 bg-gray-500 text-white rounded-full w-5 h-5 text-xs flex items-center justify-center">X</button>
                    </div>
                  ))}
                </div>
              </div>
            ))}
            <div ref={chatEndRef} />
          </div>
          <div className="p-3 border-t flex flex-col space-y-2 relative">
            {previewImages.length > 0 && (
              <div className="flex space-x-2 overflow-x-auto pb-2">
                {previewImages.map((img, index) => (
                  <div key={index} className="relative inline-block">
                    <Image src={img} alt="Preview" width={50} height={50} className="rounded-lg" />
                    <button onClick={() => removeImage(index)} className="absolute -top-1 -right-1 bg-gray-500 text-white rounded-full w-5 h-5 text-xs flex items-center justify-center">X</button>
                  </div>
                ))}
              </div>
            )}
            <div className="flex items-center space-x-2">
              <button onClick={() => setShowEmojiPicker((prev) => !prev)}>
                <Smile className="text-gray-500" />
              </button>
              {showEmojiPicker && (
                <div className="absolute bottom-14 left-0 bg-white shadow-lg p-2 rounded-lg">
                  <EmojiPicker onEmojiClick={handleEmojiSelect} />
                </div>
              )}
              <input
                type="text"
                className="flex-1 border p-2 rounded-lg"
                placeholder="Type a message..."
                value={input}
                onChange={(e) => setInput(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleSendMessage()}
              />
              <label>
                <Paperclip className="text-gray-500 cursor-pointer" />
                <input
                  type="file"
                  className="hidden"
                  accept="image/*"
                  multiple
                  onChange={handleFileChange}
                />
              </label>
              <button onClick={handleSendMessage} className="bg-indigo-600 text-white p-2 rounded-lg">
                <Send />
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

