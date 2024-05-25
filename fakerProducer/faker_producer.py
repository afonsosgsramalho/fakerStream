from faker import Faker
from kafka import KafkaProducer
import time

fake = Faker()

# Generate fake data
def generate_fake_data():
    fake_record = {
        'name': fake.first_name_nonbinary(),
        'address': fake.address(),
        'email': fake.email(),
        'phone_number': fake.phone_number(),
        'job': fake.job(),
        'company': fake.company(),
        'birthdate': fake.date_of_birth(minimum_age=18, maximum_age=90).strftime('%Y-%m-%d'),
        'credit_card_number': fake.credit_card_number(),
       'username': fake.user_name()
    }

    return fake_record


if __name__ == "__main__":
    topicName = 'fakerPerson'

    producer = KafkaProducer(bootstrap_servers = 'kafka:29092')

    while True:
        fake_record = generate_fake_data()
        producer.send(topicName, bytes(str(fake_record), 'utf-8'))
        producer.flush()
        time.sleep(0.5)
        
        print(fake_record)

