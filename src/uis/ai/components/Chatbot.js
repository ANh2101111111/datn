/* eslint-disable @next/next/no-img-element */
/* eslint-disable @typescript-eslint/no-unused-vars */
// "use client";
// import { useState, useEffect, useRef } from "react";
// import axios from "axios";
// import { MessageSquare, X, Send } from "lucide-react";

// export default function Chatbot() {
//     const [isOpen, setIsOpen] = useState(false);
//     const [messages, setMessages] = useState([]);
//     const [input, setInput] = useState("");
//     const [loading, setLoading] = useState(false);
//     const [language, setLanguage] = useState("en"); // Mặc định là tiếng Anh
//     const chatEndRef = useRef(null);
//     const API_URL = process.env.NEXT_PUBLIC_API_URL || "";

//     useEffect(() => {
//         chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
//     }, [messages]);

//     const sendMessage = async () => {
//         if (!input.trim()) return;
//         const newMessages = [...messages, { sender: "user", text: input }];
//         setMessages(newMessages);
//         setLoading(true);

//         try {
//             // Câu hỏi mẫu
//             if (input.toLowerCase().includes("hello") || input.toLowerCase().includes("xin chào") || input.toLowerCase().includes("bonjour")) {
//                 const greetingResponse = "Hello! How can I assist you today?";
//                 setMessages([...newMessages, { sender: "bot", text: greetingResponse }]);
//                 setLoading(false);
//                 setInput("");
//                 return;
//             }

//             if (input.toLowerCase().includes("how many products") || input.toLowerCase().includes("có bao nhiêu sản phẩm") || input.toLowerCase().includes("combien de produits")) {
//                 const productCountResponse = "There are 62 products available."; // Thay 'X' bằng số sản phẩm thực tế
//                 setMessages([...newMessages, { sender: "bot", text: productCountResponse }]);
//                 setLoading(false);
//                 setInput("");
//                 return;
//             }

//             if (input.toLowerCase().includes("how many categories") || input.toLowerCase().includes("có bao nhiêu danh mục") || input.toLowerCase().includes("combien de catégories")) {
//                 const categoryCountResponse = "There are 9 categories available."; // Thay 'Y' bằng số danh mục thực tế
//                 setMessages([...newMessages, { sender: "bot", text: categoryCountResponse }]);
//                 setLoading(false);
//                 setInput("");
//                 return;
//             }

//             // Dịch câu hỏi nếu ngôn ngữ không phải là tiếng Anh
//             const translatedMessage = await translateMessage(input);

//             const response = await axios.post(
//                 `${API_URL}/user/chatbot/ask`,
//                 { message: translatedMessage, language: "en" }, // Gửi luôn bằng tiếng Anh
//                 { headers: { "Content-Type": "application/json" } }
//             );

//             const { names = [], descriptions = [], images = [], originalPrice = [], discountedPrices = [] } = response.data;

//             if (names.length > 0) {
//                 const botMessages = names.map((name, index) => ({
//                     sender: "bot",
//                     text: (
//                         <>
//                             <strong>{name}</strong><br />
//                             {descriptions[index] || ""}<br />
//                             {originalPrice[index] ? `Original Price: ${originalPrice[index]} $` : ""}<br />
//                             {discountedPrices[index] ? `Discounted Price: ${discountedPrices[index]} $` : ""}
//                         </>
//                     ),
//                     image: images[index] || null,
//                 }));

//                 setMessages([...newMessages, ...botMessages]);
//             } else {
//                 setMessages([...newMessages, { sender: "bot", text: "Sorry, I couldn't find any products." }]);
//             }
//         } catch (error) {
//             console.error(error);
//             setMessages([...newMessages, { sender: "bot", text: "An error occurred!" }]);
//         } finally {
//             setLoading(false);
//         }

//         setInput("");
//     };

//     const translateMessage = async (text) => {
//         if (language !== "en") {
//             try {
//                 const response = await axios.post(`${API_URL}/translate`, { text, targetLang: "en" });
//                 return response.data.translatedText || text; // Trả về văn bản gốc nếu dịch thất bại
//             } catch (error) {
//                 console.error("Translation failed:", error);
//                 return text; // Trả về văn bản gốc nếu có lỗi
//             }
//         }
//         return text; // Nếu ngôn ngữ là tiếng Anh, không cần dịch
//     };

//     return (
//         <div className="fixed bottom-6 right-6 z-50">
//             {isOpen ? (
//                 <div className="w-80 bg-white rounded-lg shadow-lg border border-gray-300">
//                     <div className="flex justify-between bg-blue-600 text-white px-4 py-3">
//                         <span className="font-semibold">Chatbot AI</span>
//                         <X className="cursor-pointer" onClick={() => setIsOpen(false)} />
//                     </div>
//                     <div className="h-64 overflow-y-auto p-3 bg-gray-50">
//                         {messages.map((msg, index) => (
//                             <div key={index} className={`mb-3 ${msg.sender === "user" ? "text-right" : "text-left"}`}>
//                                 <div className={`inline-block px-3 py-2 rounded-lg ${msg.sender === "user" ? "bg-blue-500 text-white" : "bg-gray-300 text-black"}`}>
//                                     {msg.text}
//                                     {msg.image && <img src={msg.image} alt={msg.text} className="mt-2 w-32 h-auto rounded" />}
//                                 </div>
//                             </div>
//                         ))}
//                         <div ref={chatEndRef} />
//                     </div>
//                     <div className="flex items-center p-2 border-t border-gray-300">
//                         <input
//                             type="text"
//                             value={input}
//                             onChange={(e) => setInput(e.target.value)}
//                             onKeyPress={(e) => {
//                                 if (e.key === 'Enter') sendMessage();
//                             }}
//                             className="flex-grow border rounded-lg p-2"
//                             placeholder="Type your message..."
//                         />
//                         <button onClick={sendMessage} className="ml-2 bg-blue-600 text-white rounded-lg p-2">
//                             <Send size={20} />
//                         </button>
//                     </div>
//                 </div>
//             ) : (
//                 <div className="bg-blue-600 text-white rounded-full p-4 shadow-lg cursor-pointer" onClick={() => setIsOpen(true)}>
//                     <MessageSquare size={24} />
//                 </div>
//             )}
//         </div>
//     );
// }
"use client";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import { MessageSquare, X, Send } from "lucide-react";

export default function Chatbot() {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");
    const [loading, setLoading] = useState(false);
    const [language, setLanguage] = useState("en"); // Mặc định là tiếng Anh
    const chatEndRef = useRef(null);
    const API_URL = process.env.NEXT_PUBLIC_API_URL || "";

    useEffect(() => {
        chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages]);

    const sendMessage = async () => {
        if (!input.trim()) return;
        const newMessages = [...messages, { sender: "user", text: input }];
        setMessages(newMessages);
        setLoading(true);

        try {
            // Xử lý các câu hỏi mẫu
            if (input.toLowerCase().includes("hello") || input.toLowerCase().includes("xin chào")) {
                const greetingResponse = "Hello! How can I assist you today?";
                setMessages([...newMessages, { sender: "bot", text: greetingResponse }]);
                setLoading(false);
                setInput("");
                return;
            }
            
            if (input.toLowerCase().includes("how many products") || input.toLowerCase().includes("có bao nhiêu sản phẩm") || input.toLowerCase().includes("combien de produits")) {
                                const productCountResponse = "There are 62 products available."; // Thay 'X' bằng số sản phẩm thực tế
                                setMessages([...newMessages, { sender: "bot", text: productCountResponse }]);
                                setLoading(false);
                                setInput("");
                                return;
                            }
                
            if (input.toLowerCase().includes("how many categories") || input.toLowerCase().includes("có bao nhiêu danh mục") || input.toLowerCase().includes("combien de catégories")) {
                                const categoryCountResponse = "There are 9 categories available."; // Thay 'Y' bằng số danh mục thực tế
                                setMessages([...newMessages, { sender: "bot", text: categoryCountResponse }]);
                                setLoading(false);
                                setInput("");
                                return;
                            }
                         if (
                                input.toLowerCase().includes("list categories") || 
                                input.toLowerCase().includes("danh sách danh mục") || 
                                input.toLowerCase().includes("quelles sont les categories")
                            ) {
                                const categories = [
                                    "Folding Bike", 
                                    "Electric Bike", 
                                    "Mountain Bikes", 
                                    "Kids Bikes", 
                                    "Sports Bikes", 
                                    "Road Racing Bikes", 
                                    "Classic Bikes"
                                ];
                                
                                const categoryListResponse = `The available categories are: ${categories.join(", ")}.`;
                                setMessages([...newMessages, { sender: "bot", text: categoryListResponse }]);
                                setLoading(false);
                                setInput("");
                                return;
                            }                            

            const translatedMessage = await translateMessage(input);
            const response = await axios.post(
                `${API_URL}/user/chatbot/ask`,
                { message: translatedMessage, language: "en" },
                { headers: { "Content-Type": "application/json" } }
            );

            const { bicycleId = [], names = [], descriptions = [], images = [], originalPrice = [], discountedPrices = [] } = response.data;

            if (names.length > 0) {
                const botMessages = names.map((name, index) => ({
                    sender: "bot",
                    text: (
                        <>
                            <strong>{name}</strong><br />
                            {descriptions[index] || ""}<br />
                            {originalPrice[index] ? `Original Price: ${originalPrice[index]} $` : ""}<br />
                            {discountedPrices[index] ? `Discounted Price: ${discountedPrices[index]} $` : ""}
                            <br />
                            {images[index] && <img src={images[index]} alt={name} className="mt-2 w-32 h-auto rounded" />}
                            <br />
                            <a href={`http://localhost:3000/product/${bicycleId[index]}`} target="_blank" rel="noopener noreferrer">
                                View product details
                            </a>
                        </>
                    ),
                    image: images[index] || null,
                }));

                setMessages([...newMessages, ...botMessages]);
            } else {
                setMessages([...newMessages, { sender: "bot", text: "Sorry, I couldn't find any products." }]);
            }
        } catch (error) {
            console.error(error);
            setMessages([...newMessages, { sender: "bot", text: "An error occurred!" }]);
        } finally {
            setLoading(false);
        }

        setInput("");
    };

    const translateMessage = async (text) => {
        if (language !== "en") {
            try {
                const response = await axios.post(`${API_URL}/translate`, { text, targetLang: "en" });
                return response.data.translatedText || text; // Trả về văn bản gốc nếu dịch thất bại
            } catch (error) {
                console.error("Translation failed:", error);
                return text; // Trả về văn bản gốc nếu có lỗi
            }
        }
        return text; // Nếu ngôn ngữ là tiếng Anh, không cần dịch
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
                            onKeyPress={(e) => {
                                if (e.key === 'Enter') sendMessage();
                            }}
                            className="flex-grow border rounded-lg p-2"
                            placeholder="Type your message..."
                        />
                        <button onClick={sendMessage} className="ml-2 bg-blue-600 text-white rounded-lg p-2">
                            <Send size={20} />
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