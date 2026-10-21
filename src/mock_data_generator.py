import csv
import random
from datetime import datetime, timedelta

# Realistic ranges for marine environments
TEMP_MIN = -2.0  # °C (Antarctic waters)
TEMP_MAX = 32.0  # °C (Tropical reefs)
SALINITY_MIN = 30.0  # PSU (Open ocean)
SALINITY_MAX = 40.0  # PSU (Dead Sea levels, rare but possible)
ACOUSTIC_PING_PREFIX = "PING-"

SPECIES_LIST = [
    "Humpback Whale", "Great White Shark", "Bluefin Tuna", 
    "Bottlenose Dolphin", "Manta Ray", "Green Sea Turtle"
]
LOCATIONS_LIST = [
    "North Atlantic Gyre", "South Pacific Equatorial", 
    "Indian Ocean Monsoon Zone", "Arctic Circle"
]

def generate_acoustic_ping_id():
    return f"{ACOUSTIC_PING_PREFIX}{random.randint(10000, 99999)}"

def simulate_data(num_records, filename="mock_sensor_data.csv"):
    with open(filename, mode='w', newline='') as file:
        writer = csv.writer(file)
        # Header
        writer.writerow(["timestamp", "temperature_c", "salinity_psu", "acoustic_ping_id", "species", "location"])
        
        for _ in range(num_records):
            temp = round(random.uniform(TEMP_MIN, TEMP_MAX), 2)
            salinity = round(random.uniform(SALINITY_MIN, SALINITY_MAX), 2)
            ping_id = generate_acoustic_ping_id()
            species = random.choice(SPECIES_LIST)
            location = random.choice(LOCATIONS_LIST)
            timestamp = datetime.now() - timedelta(days=random.randint(0, 365))

            writer.writerow([timestamp.isoformat(), temp, salinity, ping_id, species, location])
    
    print(f"Generated {num_records} records in {filename}")

if __name__ == "__main__":
    simulate_data(50)