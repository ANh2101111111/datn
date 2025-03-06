"use client";

import { useState, useEffect, useRef } from "react";
import Image from "next/image";
import EmojiPicker from "emoji-picker-react";
import { Send, Paperclip, Smile, X, MessageCircle } from "lucide-react";
import "tailwindcss/tailwind.css";

interface Message {
  role: "user" | "bot";
  text: string;
  image?: string;
}

const API_KEY = "AIzaSyBA8HLmozhNYzV2Xx1989krtBjnMY3NIhM"; // API Key Gemini của bạn
const API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

export default function Chatbot() {
  const [messages, setMessages] = useState<Message[]>([]);
  const [input, setInput] = useState("");
  const [showEmojiPicker, setShowEmojiPicker] = useState(false);
  const [file, setFile] = useState<File | null>(null);
  const [isCollapsed, setIsCollapsed] = useState(true);
  const chatEndRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleSendMessage = async () => {
    if (!input.trim() && !file) return;

    const newMessage: Message = { role: "user", text: input, image: file ? URL.createObjectURL(file) : undefined };
    setMessages((prev) => [...prev, newMessage]);
    setInput("");
    setFile(null);

    const botReply: Message = { role: "bot", text: "Processing..." };
    setMessages((prev) => [...prev, botReply]);

    try {
      const response = await fetch(`${API_URL}?key=${API_KEY}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          contents: [{ parts: [{ text: input }] }],
        }),
      });

      const data = await response.json();
      console.log("API Response:", data);

      const botMessage = data?.candidates?.[0]?.content?.parts?.[0]?.text || "Error: No response from AI.";

      setMessages((prev) => [...prev.slice(0, -1), { role: "bot", text: botMessage }]);
    } catch (error) {
      console.error("Error fetching response:", error);
      setMessages((prev) => [...prev.slice(0, -1), { role: "bot", text: "Error: Unable to get response." }]);
    }
  };

  const handleEmojiSelect = (emoji: any) => {
    setInput((prev) => prev + emoji.native);
    setShowEmojiPicker(false);
  };

  return (
    <div className="fixed bottom-6 right-6 z-50">
      {/* Biểu tượng thu gọn */}
      {isCollapsed ? (
        <button
          className="w-14 h-14 bg-indigo-600 rounded-full shadow-lg flex items-center justify-center text-white"
          onClick={() => setIsCollapsed(false)}
        >
          <MessageCircle size={28} />
        </button>
      ) : (
        // Chatbox mở rộng
        <div className="w-80 bg-white rounded-xl shadow-lg overflow-hidden">
          {/* Header */}
          <div className="bg-indigo-600 text-white p-4 flex justify-between items-center">
            <h3 className="font-bold">Chatbot</h3>
            <button onClick={() => setIsCollapsed(true)} className="text-white">
              <X size={20} />
            </button>
          </div>

          {/* Nội dung chat */}
          <div className="p-4 h-96 overflow-y-auto">
            {messages.map((msg, index) => (
              <div key={index} className={`mb-3 flex ${msg.role === "user" ? "justify-end" : "justify-start"}`}>
                <div className={`p-3 rounded-lg ${msg.role === "user" ? "bg-indigo-500 text-white" : "bg-gray-200"}`}>
                  {msg.text}
                  {msg.image && <Image src={msg.image} alt="Uploaded" width={100} height={100} className="mt-2 rounded-lg" />}
                </div>
              </div>
            ))}
            <div ref={chatEndRef} />
          </div>

          {/* Input chat */}
          <div className="p-3 border-t flex items-center space-x-2 relative">
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
                onChange={(e) => setFile(e.target.files ? e.target.files[0] : null)}
              />
            </label>
            <button onClick={handleSendMessage} className="bg-indigo-600 text-white p-2 rounded-lg">
              <Send />
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
