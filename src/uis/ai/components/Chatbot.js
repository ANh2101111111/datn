"use client";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import { MessageSquare, X, Send } from "lucide-react";

export default function Chatbot() {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");
    const [loading, setLoading] = useState(false);
    const chatEndRef = useRef(null);
    const API_URL = "http://127.0.0.1:5000"; // Flask API URL

    useEffect(() => {
        chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages]);

    const sendMessage = async () => {
        if (!input.trim()) return;

        const newMessages = [...messages, { sender: "user", text: input }];
        setMessages(newMessages);
        setLoading(true);
        setInput("");

        try {
            const response = await axios.post(
                `${API_URL}/user/chatbot/ask`,
                { message: input },
                { headers: { "Content-Type": "application/json" } }
            );

            const { bicycleId = [], names = [], descriptions = [], images = [], originalPrice = [], discountedPrices = [] } = response.data;

            if (names.length > 0) {
                const botMessages = names.map((name, index) => ({
                    sender: "bot",
                    text: (
                        <div>
                            <strong>{name}</strong><br />
                            {descriptions[index] || ""}<br />
                            {originalPrice[index] ? `Original Price: ${originalPrice[index]} $` : ""}<br />
                            {discountedPrices[index] ? `Discounted Price: ${discountedPrices[index]} $` : ""}
                            <br />
                            {images[index] && <img src={images[index]} alt={name} className="mt-2 w-32 h-auto rounded" />}
                            <br />
                            <a href={`http://localhost:3000/product/${bicycleId[index]}`} target="_blank" rel="noopener noreferrer">View product details</a>
                        </div>
                    ),
                }));

                setMessages([...newMessages, ...botMessages]);
            } else {
                setMessages([...newMessages, { sender: "bot", text: "Sorry, I couldn't find any products." }]);
            }
        } catch (error) {
            console.error("Lỗi API:", error);
            setMessages([...newMessages, { sender: "bot", text: "An error occurred!" }]);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="fixed bottom-6 right-6 z-50">
            {isOpen ? (
                <div className="w-80 bg-white rounded-lg shadow-lg border border-gray-300">
                    <div className="flex justify-between bg-blue-600 text-white px-4 py-3">
                        <span className="font-semibold">Chatbot AI</span>
                        <X className="cursor-pointer" onClick={() => setIsOpen(false)} />
                    </div>
                    <div className="h-64 overflow-y-auto p-3 bg-gray-50">
                        {messages.map((msg, index) => (
                            <div key={index} className={`mb-3 ${msg.sender === "user" ? "text-right" : "text-left"}`}>
                                <div className={`inline-block px-3 py-2 rounded-lg ${msg.sender === "user" ? "bg-blue-500 text-white" : "bg-gray-300 text-black"}`}>
                                    {msg.text}
                                </div>
                            </div>
                        ))}
                        <div ref={chatEndRef} />
                    </div>
                    <div className="flex items-center p-2 border-t border-gray-300">
                        <input
                            type="text"
                            value={input}
                            onChange={(e) => setInput(e.target.value)}
                            onKeyDown={(e) => e.key === "Enter" && sendMessage()}
                            className="flex-grow border rounded-lg p-2"
                            placeholder="Type your message..."
                        />
                        <button onClick={sendMessage} className="ml-2 bg-blue-600 text-white rounded-lg p-2" disabled={loading}>
                            {loading ? "..." : <Send size={20} />}
                        </button>
                    </div>
                </div>
            ) : (
                <div className="bg-blue-600 text-white rounded-full p-4 shadow-lg cursor-pointer" onClick={() => setIsOpen(true)}>
                    <MessageSquare size={24} />
                </div>
            )}
        </div>
    );
}
