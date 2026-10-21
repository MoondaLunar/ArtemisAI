from crewai import Agent, Task, Crew
from langchain_community.llms import Ollama

# Initialize Ollama LLM
llm = Ollama(model="llama3") # Replace with your actual model name

# Define Agents
sensor_fusion_agent = Agent(
    role='Sensor Data Analyst',
    goal='Aggregate and validate sensor data from multiple sources',
    backstory='Expert in marine telemetry and data cleaning.',
    llm=llm,
    verbose=True
)

species_id_agent = Agent(
    role='Marine Biologist',
    goal='Identify species from acoustic and visual data',
    backstory='Specialist in cetacean and elasmobranch identification.',
    llm=llm,
    verbose=True
)

migration_tracker_agent = Agent(
    role='Migration Analyst',
    goal='Track movement patterns over time',
    backstory='Data scientist focused on seasonal migration routes.',
    llm=llm,
    verbose=True
)

threat_detector_agent = Agent(
    role='Security Specialist',
    goal='Detect anomalies indicating poaching or pollution',
    backstory='Former coast guard analyst turned conservation AI.',
    llm=llm,
    verbose=True
)

report_generator_agent = Agent(
    role='Report Writer',
    goal='Synthesize findings into actionable reports',
    backstory='Technical writer for NOAA and conservation NGOs.',
    llm=llm,
    verbose=True
)

# Define Tasks (Placeholders)
task_fusion = Task(
    description="Process raw sensor data and flag anomalies.",
    agent=sensor_fusion_agent
)

task_species = Task(
    description="Identify species based on acoustic signatures.",
    agent=species_id_agent
)

# Create Crew
crew = Crew(
    agents=[
        sensor_fusion_agent, 
        species_id_agent, 
        migration_tracker_agent, 
        threat_detector_agent, 
        report_generator_agent
    ],
    tasks=[task_fusion, task_species], # Add more tasks here
    verbose=True
)

# To run: crew.kickoff()