

|File|Ollama Output (Hallucinated)|Human Correction (Working)|Lesson Learned|
|-|-|-|-|
|docker-compose.yml|Wrong port (5000), fake DB vars in Ollama|Correct port (11434), clean separation|Infrastructure Logic: LLMs don't understand container networking well.|
|mock\_data\_generator.py|Printed to console only|Saved to CSV for ingestion|Data Pipelines: LLMs forget downstream consumers need file I/O.|
|agents.py|Empty classes, no imports|Full imports, Crew definition|Library Knowledge: LLMs invent class structures; humans must verify APIs.|
|Application.java|Truncated mid-line!|Complete, valid Java|Output Limits: LLMs cut off long responses; humans must finish the code.|



